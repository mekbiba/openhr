package com.openhr.benefittype.form;

import org.apache.struts.action.ActionForm;

public class BenefitTypeForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Double cap;

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

    public Double getCap() {
        return cap;
    }

    public void setCap(Double cap) {
        this.cap = cap;
    }

}
