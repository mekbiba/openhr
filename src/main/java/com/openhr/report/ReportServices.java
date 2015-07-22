/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.report;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xmen
 */
public class ReportServices {

    /*
     * This method takes two arguments
     * @reportType and @param
     */
    public static String generate(String reportType, HRReportParam param,
            HttpServletResponse response) throws Exception {

        if (reportType.equals("HR Report")) {
            //

        }

        if (reportType.equals("ACCNTNG Report")) {
            //
        }

        if (reportType.equals("ADMIN Report")) {
            //
        }

        return "<h2>If you are seeing this, it means your report is ready!</h2>";

    }

}
