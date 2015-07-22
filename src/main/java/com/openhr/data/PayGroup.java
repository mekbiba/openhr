/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.data;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author xmen
 */
@Entity
@Table(name = "paygroup", catalog = "freehrms", schema = "")
@NamedQueries({
    @NamedQuery(name = "PayGroup.findAll", query = "SELECT p FROM PayGroup p"),
    @NamedQuery(name = "PayGroup.findById", query = "SELECT p FROM PayGroup p WHERE p.id = :id"),
    @NamedQuery(name = "PayGroup.findByName", query = "SELECT p FROM PayGroup p WHERE p.name = :name"),
    @NamedQuery(name = "PayGroup.findByPayRate", query = "SELECT p FROM PayGroup p WHERE p.payRate = :payRate")})
public class PayGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic(optional = false)
    @Column(name = "payRate", nullable = false)
    private double payRate;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "payGroupId")
     private List<EmployeePayGroup> employeePayGroupCollection;*/

    public PayGroup() {
    }

    public PayGroup(Integer id) {
        this.id = id;
    }

    public PayGroup(Integer id, String name, double payRate) {
        this.id = id;
        this.name = name;
        this.payRate = payRate;
    }

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

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    /*public List<EmployeePayGroup> getEmployeePayGroupCollection() {
     return employeePayGroupCollection;
     }

     public void setEmployeePayGroupCollection(List<EmployeePayGroup> employeePayGroupCollection) {
     this.employeePayGroupCollection = employeePayGroupCollection;
     }*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PayGroup)) {
            return false;
        }
        PayGroup other = (PayGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.PayGroup[id=" + id + "]";
    }

}
