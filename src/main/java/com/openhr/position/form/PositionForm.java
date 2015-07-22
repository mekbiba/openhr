/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.position.form;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author Mekbib
 */
public class PositionForm extends ActionForm {

    private String name;
    private double salary;
    private double raisePerYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRaisePerYear() {
        return raisePerYear;
    }

    public void setRaisePerYear(double raisePerYear) {
        this.raisePerYear = raisePerYear;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
