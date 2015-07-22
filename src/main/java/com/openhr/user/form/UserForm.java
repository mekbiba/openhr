package com.openhr.user.form;

import org.apache.struts.action.ActionForm;

import com.openhr.data.Employee;
import com.openhr.data.Roles;

public class UserForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;

    private Employee employeeId;
    private Roles roleId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

}
