/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.employee.action;

import static com.openhr.factories.EmployeeFactory.update;
import static com.openhr.factories.PositionFactory.findById;
import static net.sf.json.JSONArray.fromObject;
import static net.sf.json.JSONArray.toCollection;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.openhr.data.Employee;
import com.openhr.data.EmployeeForm;
import com.openhr.data.Position;

/**
 *
 * @author Mekbib
 */
public class UpdateEmployeeAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //EmployeeForm EmployeeForm = (EmployeeForm) form;
        BufferedReader bf = request.getReader();
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        JSONArray json = fromObject(sb.toString());
        Collection<EmployeeForm> aCollection = toCollection(json, EmployeeForm.class);

        System.out.println("UPDATE Employee JSON " + json.toString());

        Employee e = new Employee();
        for (EmployeeForm eFromJSON : aCollection) {
            e.setId(eFromJSON.getId());
            e.setEmployeeId(eFromJSON.getEmployeeId());
            e.setFirstname(eFromJSON.getFirstname());
            e.setMiddlename(eFromJSON.getMiddlename());
            e.setLastname(eFromJSON.getLastname());
            e.setSex(eFromJSON.getSex());
            e.setBirthdate(new Date(eFromJSON.getBirthdate()));
            System.out.println("BIRTHDATE = " + eFromJSON.getBirthdate());
            e.setHiredate(new Date(eFromJSON.getHiredate()));
            Position p = findById(eFromJSON.getPositionId()).get(0);
            e.setPositionId(p);
            if (request.getAttribute("photo-name") != null) {
                e.setPhoto(request.getAttribute("photo-name").toString());
            } else {
                e.setPhoto(eFromJSON.getPhoto());
            }
            e.setStatus(eFromJSON.getStatus());
            update(e);
        }

        return map.findForward("");
    }
}
