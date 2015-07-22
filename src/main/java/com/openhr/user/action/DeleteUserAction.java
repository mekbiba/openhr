package com.openhr.user.action;

import static com.openhr.factories.UsersFactory.delete;
import static net.sf.json.JSONArray.fromObject;
import static net.sf.json.JSONArray.toCollection;

import java.io.BufferedReader;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.openhr.data.Users;
import com.openhr.user.form.UserForm;

public class DeleteUserAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //EmployeeForm EmployeeForm = (EmployeeForm) form;
        BufferedReader bf = request.getReader();
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        JSONArray json = fromObject(sb.toString());
        Collection<UserForm> aCollection = toCollection(json, UserForm.class);

        System.out.println("EMployee JSON " + json.toString());

        Users u = new Users();
        for (UserForm uFromJSON : aCollection) {
            u.setId(uFromJSON.getId());
            delete(u);
        }
        return map.findForward("");
    }
}
