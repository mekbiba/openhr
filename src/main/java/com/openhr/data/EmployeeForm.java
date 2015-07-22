package com.openhr.data;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class EmployeeForm extends ActionForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String employeeId;
    private String firstname;
    private String middlename;
    private String lastname;
    private String sex;
    private Date birthdate;
    private Date hiredate;
    private String photo;
    private Integer positionId;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getBirthdate() {
        return birthdate.getTime();
    }

    public void setBirthdate(long birthdate) {
        this.birthdate = new Date(birthdate);
    }

    public long getHiredate() {
        return hiredate.getTime();
    }

    public void setHiredate(long hiredate) {
        this.hiredate = new Date(hiredate);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
