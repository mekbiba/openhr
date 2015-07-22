package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.LeaveRequest;

public class LeaveRequestFactory {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Session session;
    private static Query query;
    private static List<LeaveRequest> leaveRequests;

    public LeaveRequestFactory() {
    }

    public static List<LeaveRequest> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("LeaveRequest.findAll");
        leaveRequests = query.list();
        session.getTransaction().commit();

        return leaveRequests;
    }

    public static List<LeaveRequest> findById(Integer leaveId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("LeaveRequest.findById");
        query.setInteger(0, leaveId);
        leaveRequests = query.list();
        session.getTransaction().commit();

        return leaveRequests;
    }

    public static List<LeaveRequest> findByLeaveDate(Date leaveDate) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("LeaveRequest.findByLeaveDate");
        query.setDate(0, leaveDate);
        leaveRequests = query.list();
        session.getTransaction().commit();

        return leaveRequests;
    }

    public static List<LeaveRequest> findByReturnDate(Date returnDate) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("LeaveRequest.findByReturnDate");
        query.setDate(0, returnDate);
        leaveRequests = query.list();
        session.getTransaction().commit();

        return leaveRequests;
    }

    public static List<LeaveRequest> findByStatus(Integer status) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("LeaveRequest.findByStatus");
        query.setInteger(0, status);
        leaveRequests = query.list();
        session.getTransaction().commit();

        return leaveRequests;
    }

    public static List<LeaveRequest> findByNoOfDays(Integer noOfDays) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("LeaveRequest.findByNoOfDays");
        query.setInteger(0, noOfDays);
        leaveRequests = query.list();
        session.getTransaction().commit();

        return leaveRequests;
    }

    public static boolean delete(LeaveRequest l) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            LeaveRequest leave = (LeaveRequest) session.get(LeaveRequest.class, l.getId());
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

    public static boolean insert(LeaveRequest l) {
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

    public static boolean update(LeaveRequest l) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        try {
            LeaveRequest leaveRequest = (LeaveRequest) session.get(LeaveRequest.class, l.getId());
            leaveRequest.setLeaveTypeId(l.getLeaveTypeId());
            leaveRequest.setLeaveDate(new Date(l.getLeaveDate()));
            leaveRequest.setReturnDate(new Date(l.getReturnDate()));
            leaveRequest.setStatus(l.getStatus());
            leaveRequest.setNoOfDays(l.getNoOfDays());
            leaveRequest.setDescription(l.getDescription());
            leaveRequest.setEmployeeId(l.getEmployeeId());
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return done;
    }
}
