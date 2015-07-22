package com.openhr;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.EmployeePayroll;

/**
 *
 * @author xmen
 */
public class Payroll {

    private static Session session;
    private static Query query;
    private static List<EmployeePayroll> employeePayroll;

    public static List<EmployeePayroll> generatePayroll() {

        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("EmployeePayroll.findAll");
        employeePayroll = query.list();

        return employeePayroll;

    }
}
