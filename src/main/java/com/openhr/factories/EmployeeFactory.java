/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.openhr.data.Employee;

/**
 *
 * @author Mekbib
 */
@Component
public class EmployeeFactory implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Session session;
    private static Query query;
    private static List<Employee> employees;

    public  Integer findLastId() throws Exception {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Employee.findLastId");
        List<Employee> lastId = query.list();
        session.getTransaction().commit();
        if (lastId.size() != 0) {
            return lastId.get(0).getId();
        } else {
            return 0;
        }
    }

    public  List<Employee> findAll() throws Exception {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Employee.findAll");
        employees = query.list();
        session.getTransaction().commit();

        return employees;
    }

    public  List<Employee> findById(Integer employeeId) throws Exception {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Employee.findById");
        query.setInteger(0, employeeId);
        employees = query.list();
        session.getTransaction().commit();

        return employees;
    }

    public  List<Employee> findByEmployeeId(String employeeId) throws Exception {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Employee.findByEmployeeId");
        query.setString(0, employeeId);
        employees = query.list();
        session.getTransaction().commit();

        return employees;
    }

    public  List<Employee> findByName(String EmployeeName) throws Exception {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Employee.findByName");
        query.setString(0, EmployeeName);
        employees = query.list();
        session.getTransaction().commit();

        return employees;
    }

    public  List<Employee> findBySalary(Double salary) throws Exception {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Employee.findBySalary");
        query.setDouble(0, salary);
        employees = query.list();
        session.getTransaction().commit();

        return employees;
    }

    public  List<Employee> findByRaise(Double raise) throws Exception {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Employee.findByRaisePerYear");
        query.setDouble(0, raise);
        employees = query.list();
        session.getTransaction().commit();

        return employees;
    }

    public  boolean delete(Employee e) throws Exception {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        Employee emp = (Employee) session.get(Employee.class, e.getId());
        session.delete(emp);
        session.getTransaction().commit();
        session.flush();
        done = true;

        return done;
    }

    public  boolean insert(Employee e) throws Exception {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();

        session.save(e);
        session.getTransaction().commit();
        done = true;

        return done;
    }

    public  boolean update(Employee e) throws Exception {
        
        session = getInstance().getCurrentSession();
        session.beginTransaction();

		// System.out.println("ALREADY IN SESSION : "+session.contains(session.get(Employee.class,
        // e.getId())));
        Employee emp = (Employee) session.get(Employee.class, e.getId());

        emp.setEmployeeId(e.getEmployeeId());
        emp.setFirstname(e.getFirstname());
        emp.setMiddlename(e.getMiddlename());
        emp.setLastname(e.getLastname());
        emp.setSex(e.getSex());
        emp.setBirthdate(new Date(e.getBirthdate()));
        emp.setHiredate(new Date(e.getHiredate()));
        emp.setPositionId(e.getPositionId());
        emp.setPhoto(e.getPhoto());
        emp.setStatus(e.getStatus());
        session.getTransaction().commit();

        return true;
    }
}
