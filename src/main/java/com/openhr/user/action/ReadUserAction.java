package com.openhr.user.action;

import static com.openhr.factories.UsersFactory.findAll;
import static net.sf.json.JSONArray.fromObject;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.openhr.data.Users;

public class ReadUserAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
//        UserForm userForm = (UserForm) form;

        JSONArray result = null;
        try {
            List<Users> users = findAll();
            System.out.println("Size of the list that contains employees " + users.size());
            result = fromObject(users);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(result.toString());
        out.flush();

        return map.findForward("");
    }
}
