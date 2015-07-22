package com.openhr.paygroup.action;

import static com.openhr.factories.PayGroupFactory.insert;
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

import com.openhr.data.PayGroup;

public class PayGroupAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map, ActionForm form,
            HttpServletRequest request, HttpServletResponse reponse)
            throws Exception {
        BufferedReader bf = request.getReader();
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        JSONArray json = fromObject(sb.toString());
        Collection<PayGroup> aCollection = toCollection(json,
                PayGroup.class);
        PayGroup p = new PayGroup();
        for (PayGroup pFromJSON : aCollection) {
            p.setName(pFromJSON.getName());
            p.setPayRate(pFromJSON.getPayRate());
            insert(p);
        }

        return map.findForward("");
    }
}
