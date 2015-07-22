/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.Leave;
import com.openhr.data.LeaveApproval;

/**
 *
 * @author Mekbib
 */
public class LeaveFactory {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Session session;
    private static Query query;
    private static List<Leave> leaves;

    public LeaveFactory() {
    }

    public static List<Leave> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Leave.findAll");
        leaves = query.list();
        session.getTransaction().commit();

        return leaves;
    }

    public static List<Leave> findById(Integer leaveId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Leave.findById");
        query.setInteger(0, leaveId);
        leaves = query.list();
        session.getTransaction().commit();

        return leaves;
    }

    public static List<Leave> findByUnusedDays(Integer unusedDays) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Leave.findUnuseddays");
        query.setInteger(0, unusedDays);
        leaves = query.list();
        session.getTransaction().commit();

        return leaves;
    }

    public static List<Leave> findByYear(Integer year) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Leave.findByYear");
        query.setInteger(0, year);
        leaves = query.list();
        session.getTransaction().commit();

        return leaves;
    }

    public static boolean delete(Leave l) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        try {
            Leave leave = (Leave) session.get(Leave.class, l.getId());
            session.delete(leave);
            session.flush();
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return done;
    }

    public static boolean insert(Leave l) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(l);
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return done;
    }

    /**
     * ******insert leave approval************
     */
    public static boolean insert(LeaveApproval l) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(l);
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return done;
    }

    /**
     * *****end of insert for leave approval**
     */

    public static boolean update(Leave l) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        try {
            Leave leave = (Leave) session.get(Leave.class, l.getId());
            leave.setLeaveType(l.getLeaveType());
            leave.setUnuseddays(l.getUnuseddays());
            leave.setYear(l.getYear());
            leave.setEmployeeId(l.getEmployeeId());
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return done;
    }
}
