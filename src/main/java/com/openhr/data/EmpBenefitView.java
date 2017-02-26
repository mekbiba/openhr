/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.data;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xmen
 */
@Entity
@Table(name = "emp_benefit_view", catalog = "freehrms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpBenefitView.findAll", query = "SELECT e FROM EmpBenefitView e"),
    @NamedQuery(name = "EmpBenefitView.findAvailablBenefitType", query = "SELECT distinct e.benefitType FROM EmpBenefitView e"),
    @NamedQuery(name = "EmpBenefitView.findByEmpId", query = "SELECT e FROM EmpBenefitView e WHERE e.empId = :empId"),
    @NamedQuery(name = "EmpBenefitView.findBySalary", query = "SELECT e FROM EmpBenefitView e WHERE e.salary = :salary"),
    @NamedQuery(name = "EmpBenefitView.findByBenefitAmnt", query = "SELECT e FROM EmpBenefitView e WHERE e.benefitAmnt = :benefitAmnt"),
    @NamedQuery(name = "EmpBenefitView.findByBenefitType", query = "SELECT e FROM EmpBenefitView e WHERE e.benefitType = :benefitType")})
public class EmpBenefitView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "EMP_ID", nullable = false, length = 45)
    @Id
    private String empId;
    @Basic(optional = false)
    @Column(name = "SALARY", nullable = false)
    private double salary;
    @Basic(optional = false)
    @Column(name = "BENEFIT_AMNT", nullable = false)
    private double benefitAmnt;
    @Basic(optional = false)
    @Column(name = "BENEFIT_TYPE", nullable = false, length = 45)
    private String benefitType;

    public EmpBenefitView() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBenefitAmnt() {
        return benefitAmnt;
    }

    public void setBenefitAmnt(double benefitAmnt) {
        this.benefitAmnt = benefitAmnt;
    }

    public String getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }

}
