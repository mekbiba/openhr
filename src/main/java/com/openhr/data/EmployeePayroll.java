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
@Table(name = "emp_payroll_view", catalog = "freehrms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeePayroll.findAll", query = "SELECT e FROM EmployeePayroll e"),
    @NamedQuery(name = "EmployeePayroll.findByEmployeeid", query = "SELECT e FROM EmployeePayroll e WHERE e.employeeid = :employeeid"),
    @NamedQuery(name = "EmployeePayroll.findByFullName", query = "SELECT e FROM EmployeePayroll e WHERE e.fullName = :fullName"),
    @NamedQuery(name = "EmployeePayroll.findByGrossSalary", query = "SELECT e FROM EmployeePayroll e WHERE e.grossSalary = :grossSalary"),
    @NamedQuery(name = "EmployeePayroll.findByBenefitType", query = "SELECT e FROM EmployeePayroll e WHERE e.benefitType = :benefitType"),
    @NamedQuery(name = "EmployeePayroll.findByBenefitAmnt", query = "SELECT e FROM EmployeePayroll e WHERE e.benefitAmnt = :benefitAmnt")})
public class EmployeePayroll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "EMPLOYEEID", nullable = false, length = 45)
    @Id
    private String employeeid;
    @Basic(optional = false)
    @Column(name = "FULL_NAME", nullable = false, length = 137)
    private String fullName;
    @Basic(optional = false)
    @Column(name = "GROSS_SALARY", nullable = false)
    private double grossSalary;
    @Column(name = "BENEFIT_TYPE", length = 45)
    private String benefitType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BENEFIT_AMNT", precision = 22)
    private Double benefitAmnt;

    public EmployeePayroll() {
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public String getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }

    public Double getBenefitAmnt() {
        return benefitAmnt;
    }

    public void setBenefitAmnt(Double benefitAmnt) {
        this.benefitAmnt = benefitAmnt;
    }

}
