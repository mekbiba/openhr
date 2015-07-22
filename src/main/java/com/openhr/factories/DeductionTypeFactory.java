package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.DeductionType;

public class DeductionTypeFactory {

    private static final long serialVersionUID = 1L;

    private static Session session;
    private static Query query;
    private static List<DeductionType> deductionTypes;

    public DeductionTypeFactory() {

    }

    public static List<DeductionType> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("DeductionType.findAll");
        deductionTypes = query.list();
        session.getTransaction().commit();

        return deductionTypes;
    }

    public static List<DeductionType> findById(Integer deductionTypeId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("DeductionType.findById");
        query.setInteger(0, deductionTypeId);
        deductionTypes = query.list();
        session.getTransaction().commit();

        return deductionTypes;
    }

    public static List<DeductionType> findByName(String deductionTypeName) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("DeductionType.findByName");
        query.setString(0, deductionTypeName);
        deductionTypes = query.list();
        session.getTransaction().commit();

        return deductionTypes;
    }

    public static boolean delete(DeductionType dt) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            DeductionType DeductionType = (DeductionType) session.get(DeductionType.class, dt.getId());
            session.delete(DeductionType);
            session.flush();
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }

    public static boolean insert(DeductionType dt) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(dt);
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }

    public static boolean update(DeductionType dt) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        try {
            DeductionType deductionType = (DeductionType) session.get(DeductionType.class, dt.getId());
            deductionType.setName(dt.getName());
            deductionType.setDescription(dt.getDescription());
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }

}
