package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.EmpBenefitView;
import com.openhr.data.Payroll;

public class PayrollFactory {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Session session;
    private static Query query;
    private static List<Payroll> payrollList;
    private static List<EmpBenefitView> benefitList;

    public PayrollFactory() {

    }

    public static List<Payroll> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Payroll.findAll");
        payrollList = query.list();
        session.getTransaction().commit();

        return payrollList;
    }

    public static List<Payroll> findById(Integer payrollId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Payroll.findById");
        query.setInteger(0, payrollId);
        payrollList = query.list();
        session.getTransaction().commit();

        return payrollList;
    }

    public static List<Payroll> findByRunOnDate(Date runOnDate) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Payroll.findByRunOnDate");
        query.setDate(0, runOnDate);
        payrollList = query.list();
        session.getTransaction().commit();

        return payrollList;
    }

    public static boolean delete(Payroll payroll) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            Payroll p = (Payroll) session.get(Payroll.class, payroll.getId());
            session.delete(p);
            session.getTransaction().commit();
            session.flush();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }

    public static boolean insert(Payroll payroll) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(payroll);
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }

    public static boolean update(Payroll payroll) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        try {
            Payroll p = (Payroll) session.get(Payroll.class, payroll.getId());
            p.setFile(payroll.getFile());
            p.setRunBy(payroll.getRunBy());
            p.setRunOnDate(payroll.getRunOnDate());
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }

    /**
     * ** Methods for allowances and deductions**
     */
    public static List<EmpBenefitView> findAllowanceByEmpId(Integer empId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("EmpBenefitView.findByEmpId");
        query.setInteger(0, empId);
        benefitList = query.list();
        session.getTransaction().commit();

        return benefitList;
    }

}
