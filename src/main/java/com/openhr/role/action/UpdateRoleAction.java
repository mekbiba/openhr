package com.openhr.role.action;

import static com.openhr.factories.RolesFactory.update;
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

import com.openhr.data.Roles;
import com.openhr.role.form.RoleForm;

public class UpdateRoleAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BufferedReader bf = request.getReader();
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        JSONArray json = fromObject(sb.toString());
        Collection<RoleForm> aCollection = toCollection(json, RoleForm.class);

        System.out.println("Role JSON " + json.toString());

        Roles r = new Roles();
        for (RoleForm rFromJSON : aCollection) {
            r.setId(rFromJSON.getId());
            r.setName(rFromJSON.getName());
            update(r);
        }

        return map.findForward("");
    }
}
