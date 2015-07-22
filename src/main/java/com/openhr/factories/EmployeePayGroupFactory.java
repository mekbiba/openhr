package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.EmployeePayGroup;

public class EmployeePayGroupFactory {

    private static Session session;
    private static Query query;
    private static List<EmployeePayGroup> employeePayGroupList;
    private static EmployeePayGroup employeePayGroup;

    public static List<EmployeePayGroup> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("EmployeePayGroup.findAll");
        employeePayGroupList = query.list();
        session.getTransaction().commit();

        return employeePayGroupList;
    }

    public static EmployeePayGroup findById(Integer epgId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        employeePayGroup = (EmployeePayGroup) session.get(EmployeePayGroup.class, epgId);
//		query.setInteger(0, positionId);
//		positions = query.list();
        session.getTransaction().commit();

        return employeePayGroup;
    }

    public static boolean delete(EmployeePayGroup epg) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        EmployeePayGroup epg1 = (EmployeePayGroup) session.get(EmployeePayGroup.class, epg.getId());
        session.delete(epg1);
        session.getTransaction().commit();
        done = true;

        return done;
    }

    public static boolean insert(EmployeePayGroup epg) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();

        session.save(epg);
        session.getTransaction().commit();

        done = true;

        return done;
    }

    public static boolean update(EmployeePayGroup epg) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        EmployeePayGroup epg1 = (EmployeePayGroup) session.get(EmployeePayGroup.class, epg.getId());
        /*epg1.setName(epg.getName());
         epg1.setSalary(epg.getSalary());
         epg1.setRaisePerYear(epg.getRaisePerYear());*/
        session.getTransaction().commit();
        done = true;

        return done;
    }

}
