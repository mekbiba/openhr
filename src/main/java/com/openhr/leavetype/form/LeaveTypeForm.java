package com.openhr.leavetype.form;

import org.apache.struts.action.ActionForm;

public class LeaveTypeForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Double dayCap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDayCap() {
        return dayCap;
    }

    public void setDayCap(Double dayCap) {
        this.dayCap = dayCap;
    }

}
