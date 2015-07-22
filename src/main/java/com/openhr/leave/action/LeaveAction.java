package com.openhr.leave.action;

import static com.openhr.factories.LeaveFactory.insert;
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

import com.openhr.data.Leave;

public class LeaveAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse reponse) throws Exception {
        BufferedReader bf = request.getReader();
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        JSONArray json = fromObject(sb.toString());

        Collection<Leave> aCollection = toCollection(json, Leave.class);
        Leave lt = new Leave();
        for (Leave lFromJSON : aCollection) {
            lt.setLeaveType(lFromJSON.getLeaveType());
            lt.setEmployeeId(lFromJSON.getEmployeeId());
            lt.setUnuseddays(lFromJSON.getUnuseddays());
            lt.setYear(lFromJSON.getYear());
            insert(lt);
        }
        return map.findForward("");
    }
}