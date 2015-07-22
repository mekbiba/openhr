package com.openhr.role.form;

import org.apache.struts.action.ActionForm;

public class RoleForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
