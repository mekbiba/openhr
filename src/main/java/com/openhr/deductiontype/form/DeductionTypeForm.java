package com.openhr.deductiontype.form;

import org.apache.struts.action.ActionForm;

public class DeductionTypeForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

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

}
