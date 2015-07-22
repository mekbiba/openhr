/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.report.action;

import static com.openhr.report.ReportServices.generate;
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

import com.openhr.report.HRReportParam;
import com.openhr.report.Report;

/**
 *
 * @author xmen
 */
public class GenerateHRReport extends Action {

    @Override
    public ActionForward execute(ActionMapping map,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String reportType = "Human Resource Report";
        BufferedReader bf = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = fromObject(sb.toString());

        System.out.println(json.toString());

        HRReportParam param = (HRReportParam) toBean(json, HRReportParam.class);

        response.setHeader("Content-Disposition", "attachment; filename=" + reportType);
        response.setContentType("application/pdf");
        response.getWriter().print(generate(Report.HR_REPORT, param, response));
        response.getWriter().flush();
        return map.findForward("continue");
    }
}
