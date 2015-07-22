package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.PayGroup;

public class PayGroupFactory {

    private static Session session;
    private static Query query;
    private static List<PayGroup> payGroupList;
    private static PayGroup payGroup;

    public static List<PayGroup> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("PayGroup.findAll");
        payGroupList = query.list();
        session.getTransaction().commit();

        return payGroupList;
    }

    public static PayGroup findById(Integer pgId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        payGroup = (PayGroup) session.get(PayGroup.class, pgId);
//		query.setInteger(0, positionId);
//		positions = query.list();
        session.getTransaction().commit();

        return payGroup;
    }

    public static PayGroup findByName(String pgName) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("PayGroup.findByName");
        query.setString(0, pgName);
        payGroupList = query.list();
        session.getTransaction().commit();

        if (payGroupList.size() > 0) {
            payGroup = payGroupList.get(0);
        }
        return payGroup;
    }

    public static boolean delete(PayGroup pg) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        PayGroup pg1 = (PayGroup) session.get(PayGroup.class, pg.getId());
        session.delete(pg1);
        session.getTransaction().commit();
        done = true;

        return done;
    }

    public static boolean insert(PayGroup pg) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();

        session.save(pg);
        session.getTransaction().commit();
        done = true;

        return done;
    }

    public static boolean update(PayGroup pg) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        PayGroup pg1 = (PayGroup) session.get(PayGroup.class, pg.getId());
        pg1.setName(pg.getName());
        pg1.setPayRate(pg.getPayRate());
        session.getTransaction().commit();
        done = true;

        return done;
    }

}
