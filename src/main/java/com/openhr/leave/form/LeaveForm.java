package com.openhr.leave.form;

import org.apache.struts.action.ActionForm;

import com.openhr.data.Employee;
import com.openhr.data.LeaveType;

public class LeaveForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer unuseddays;
    private LeaveType leaveType;
    private Employee employeeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnuseddays() {
        return unuseddays;
    }

    public void setUnuseddays(Integer unuseddays) {
        this.unuseddays = unuseddays;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

}
