/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.report;

/**
 *
 * @author xmen
 */
public class HRReportParam extends ReportParam {

    private String department;
    private String performance;
    private String payGrade;
    private String reportType;

    //<editor-fold defaultstate="collapsed" desc="getter and setters">
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getPayGrade() {
        return payGrade;
    }

    public void setPayGrade(String payGrade) {
        this.payGrade = payGrade;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    //</editor-fold>
}
