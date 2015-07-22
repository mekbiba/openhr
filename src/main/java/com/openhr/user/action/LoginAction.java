/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.user.action;

import static com.openhr.Config.readConfig;
import static com.openhr.factories.EmployeeFactory.findAll;
import static com.openhr.factories.UsersFactory.findByUserName;
import static java.lang.Math.pow;
import static java.util.Calendar.getInstance;
import static net.sf.json.JSONObject.fromObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.openhr.Config;
import com.openhr.common.OpenHRAction;
import com.openhr.data.Employee;
import com.openhr.data.Users;
import com.openhr.user.form.LoginForm;

/**
 *
 * @author Mekbib
 */
public class LoginAction extends OpenHRAction {

    @Override
    public ActionForward execute(ActionMapping map, ActionForm form,
            HttpServletRequest request, HttpServletResponse reponse)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        Users user = null;

        user = findByUserName(loginForm.getUsername());

        if (null != user) {
            if (user.getPassword().equalsIgnoreCase(loginForm.getPassword())) {

                request.getSession().setAttribute("loggedUser",
                        loginForm.getUsername());
                request.getSession().setAttribute("employeeId",
                        user.getEmployeeId());

                request.getSession().setAttribute("loggedEmployee",
                        fromObject(user.getEmployeeId()).toString().trim());

                /**
                 * *
                 * Read open-hr.xml file and load settings to the current
                 * session
				 *
                 */
                readConfig();
                /**
                 * ******************************************************************
                 */
                String employeeIdPattern = Config.employeeIdPattern;
                String employeePromotionStrategy = Config.employeePromotionStrategy;
                String payrollStrategy = Config.payrollStrategy;

                request.getSession().setAttribute("EMPLOYEE_ID_PATTERN", employeeIdPattern);
                request.getSession().setAttribute("EMPLOYEE_PROMOTION_STRATEGY", employeePromotionStrategy);
                request.getSession().setAttribute("EMPLOYEE_PAYROLL_STRATEGY", payrollStrategy);

                /*
                 * check if any employee has reached promotion period
                 * notify if exists
                 */
                long year = (long) (3.1556926 * pow(10, 10));
                long aMonth = 30 * 24 * 60 * 60 * 1000;
                long diff = 0;
                Calendar today = getInstance();
                today.setTime(new Date());
                long todayTime = today.getTime().getTime();
                List<Employee> employees = findAll();
                List<Employee> elligibleForPromotion = new ArrayList<>();
                for (Employee e : employees) {
                    e.getHiredate();
                    diff = (todayTime - (e.getHiredate() + aMonth));
					//System.out.println(diff/(365.25*24*60*60*1000));

                    if ((todayTime - (e.getHiredate() + aMonth)) % year == 0) {
                        System.out.println("ELLIGBLE FOR PROMOTION !!!");
                        elligibleForPromotion.add(e);
                    }

                }

                if (loginForm.getRole().equalsIgnoreCase("Administrator")) {
                    return map.findForward("admin");
                } else if (loginForm.getRole().equalsIgnoreCase("HR")) {
                    return map.findForward("hr");
                } else if (loginForm.getRole().equalsIgnoreCase("Employee")) {
                    return map.findForward("member");
                } else if (loginForm.getRole().equalsIgnoreCase("Accountant")) {
                    return map.findForward("finance");
                }
            }
        } else {
            request.getSession().setAttribute("loggedUser", null);
            return map.findForward("login");
        }
        return map.findForward("login");
    }
}
