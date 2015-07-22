package com.openhr.user.action;

import static com.openhr.factories.UsersFactory.insert;
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

public class UserAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       // UserForm userForm = (UserForm) form;

        BufferedReader bf = request.getReader();
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        JSONArray json = fromObject(sb.toString());

        System.out.println("THE JSON " + json.toString());

        Collection<Users> aCollection = toCollection(json, Users.class);
        Users u = new Users();
        for (Users uFromJSON : aCollection) {
            u.setRoleId(uFromJSON.getRoleId());
            u.setEmployeeId(uFromJSON.getEmployeeId());
            u.setUsername(uFromJSON.getUsername());
            u.setPassword(uFromJSON.getPassword());
            insert(u);
        }

        return map.findForward("");
    }
}
