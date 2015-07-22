/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.factories.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.openhr.data.Benefit;
import com.openhr.data.BenefitType;
import com.openhr.data.DeductionType;
import com.openhr.data.EmpBenefitView;
import com.openhr.data.Employee;
import com.openhr.data.EmployeePayGroup;
import com.openhr.data.EmployeePayroll;
import com.openhr.data.Leave;
import com.openhr.data.LeaveApproval;
import com.openhr.data.LeaveRequest;
import com.openhr.data.LeaveType;
import com.openhr.data.PayGroup;
import com.openhr.data.Position;
import com.openhr.data.Report;
import com.openhr.data.Roles;
import com.openhr.data.Users;

/**
 *
 * @author Mekbib
 */
public class OpenHRSessionFactory {

    private static SessionFactory sessionFactory = null;

    private OpenHRSessionFactory() {
        AnnotationConfiguration config = new AnnotationConfiguration();
        config.configure();
        config.addAnnotatedClass(Benefit.class);
        config.addAnnotatedClass(BenefitType.class);
        config.addAnnotatedClass(Employee.class);
        config.addAnnotatedClass(EmployeePayroll.class);
        config.addAnnotatedClass(EmpBenefitView.class);
        config.addAnnotatedClass(Leave.class);
        config.addAnnotatedClass(LeaveType.class);
        config.addAnnotatedClass(LeaveRequest.class);
        config.addAnnotatedClass(LeaveApproval.class);
        config.addAnnotatedClass(Position.class);
        config.addAnnotatedClass(PayGroup.class);
        config.addAnnotatedClass(EmployeePayGroup.class);
        config.addAnnotatedClass(DeductionType.class);
        config.addAnnotatedClass(Report.class);
        config.addAnnotatedClass(Roles.class);
        config.addAnnotatedClass(Users.class);
        sessionFactory = config.buildSessionFactory();
    }

    public static SessionFactory getInstance() {
        if (null == sessionFactory) {
            new OpenHRSessionFactory();
            return sessionFactory;
        } else {
            return sessionFactory;
        }
    }
}
