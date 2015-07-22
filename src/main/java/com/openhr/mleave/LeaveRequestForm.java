package com.openhr.mleave;

import java.util.Date;

public class LeaveRequestForm {

    private String employeeId;
    private Integer leaveTypeId;
    private Date leaveDate;
    private Integer noOfDays;
    private Date returnDate;
    private String description;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Integer leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public long getLeaveDate() {
        return leaveDate.getTime();
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    public long getReturnDate() {
        return returnDate.getTime();
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
