/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.employee.action;

import static com.openhr.factories.EmployeeFactory.findAll;
import static net.sf.json.JSONArray.fromObject;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.openhr.data.Employee;

/**
 *
 * @author Mekbib
 */
public class ReadEmployeeAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // EmployeeForm EmployeeForm = (EmployeeForm) form;

        JSONArray result = null;
        //long start=0,end=0,diff=0;
        try {

            List<Employee> employees = findAll();
            //start=System.currentTimeMillis();
            for (Employee emp : employees) {
                System.out.println("Path to look photos for: "
                 + getServlet().getServletContext().getRealPath(
                 emp.getPhoto()));
                File photo = new File(getServlet().getServletContext()
                        .getRealPath(emp.getPhoto()));
                if (!photo.exists()) {
                    emp.setPhoto("/data/photo/placeholder-pic.png");
                }
                //System.out.println("Birthdate " + new Date(emp.getBirthdate()));
            }

            result = fromObject(employees);
			//end=System.currentTimeMillis();
            //diff = end - start;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("It took " + diff +" milli seconds to read the results");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(result.toString());
        out.flush();

        return map.findForward("");
    }
}
