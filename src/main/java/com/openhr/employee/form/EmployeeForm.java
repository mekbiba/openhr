/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.employee.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.openhr.data.Position;

/**
 *
 * @author Mekbib
 */
public class EmployeeForm extends ActionForm {

    private Integer id;
    private String employeeId;
    private String firstname;
    private String middlename;
    private String lastname;
    private String sex;
    private Date birthdate;
    private Date hiredate;
    private Position positionId;
    private String photo;

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
