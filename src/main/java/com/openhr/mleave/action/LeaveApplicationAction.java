package com.openhr.mleave.action;

import static com.openhr.factories.EmployeeFactory.findByEmployeeId;
import static com.openhr.factories.LeaveRequestFactory.insert;
import static com.openhr.factories.LeaveTypeFactory.findById;
import static net.sf.json.JSONArray.fromObject;
import static net.sf.json.JSONArray.toCollection;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.openhr.data.Employee;
import com.openhr.data.LeaveRequest;
import com.openhr.mleave.LeaveRequestForm;

public class LeaveApplicationAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BufferedReader reader = request.getReader();
        StringBuffer buffer = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        System.out.println("About to print json");
        System.out.println(buffer.toString());
        Employee emp = null;
        JSONArray json = fromObject(buffer.toString());
        LeaveRequest leaveRequest = new LeaveRequest();
        Collection<LeaveRequestForm> aCollection = toCollection(json, LeaveRequestForm.class);
        for (LeaveRequestForm lrFromJSON : aCollection) {

            List empList = findByEmployeeId(lrFromJSON.getEmployeeId());
            if (empList.size() > 0) {
                emp = (Employee) empList.get(0);
                leaveRequest.setEmployeeId(emp);
            } else {
                response.setStatus(response.SC_INTERNAL_SERVER_ERROR);

                return map.findForward("");
            }
            leaveRequest.setLeaveTypeId(findById(lrFromJSON.getLeaveTypeId()).get(0));
            leaveRequest.setLeaveDate(new Date(lrFromJSON.getLeaveDate()));
            leaveRequest.setReturnDate(new Date(lrFromJSON.getReturnDate()));
            leaveRequest.setNoOfDays(lrFromJSON.getNoOfDays());
            leaveRequest.setDescription(lrFromJSON.getDescription());
            insert(leaveRequest);
        }
        return map.findForward("");
    }
}
