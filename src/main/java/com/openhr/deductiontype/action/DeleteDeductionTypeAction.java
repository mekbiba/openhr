package com.openhr.deductiontype.action;

import static com.openhr.factories.DeductionTypeFactory.delete;
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

import com.openhr.data.DeductionType;

public class DeleteDeductionTypeAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BufferedReader bf = request.getReader();
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        JSONArray json = fromObject(sb.toString());
        Collection<DeductionType> aCollection = toCollection(json, DeductionType.class);
        DeductionType dt = new DeductionType();
        for (DeductionType dtFromJSON : aCollection) {
            dt.setId(dtFromJSON.getId());
            delete(dt);
        }

        return map.findForward("");
    }
}
