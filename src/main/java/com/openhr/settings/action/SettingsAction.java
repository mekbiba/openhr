package com.openhr.settings.action;

import static com.openhr.Config.writeConfig;
import static net.sf.json.JSONObject.fromObject;
import static net.sf.json.JSONObject.toBean;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.openhr.settings.Settings;

public class SettingsAction extends Action {

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
        System.out.println("THE JSON " + sb.toString());
        JSONObject json = fromObject(sb.toString());

        Settings settings = (Settings) toBean(json, Settings.class);
        //write back to the xml here
        writeConfig(settings);

        return map.findForward("");
    }
}
