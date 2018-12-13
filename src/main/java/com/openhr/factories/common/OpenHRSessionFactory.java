/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.factories.common;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
        Configuration config = new Configuration();
		config.configure(new File("hibernate.cfg.xml"));
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
