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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author xmen
 */
@Entity
@Table(name = "employeepaygroup", catalog = "freehrms", schema = "")
@NamedQueries({
    @NamedQuery(name = "EmployeePayGroup.findAll", query = "SELECT e FROM EmployeePayGroup e"),
    @NamedQuery(name = "EmployeePayGroup.findById", query = "SELECT e FROM EmployeePayGroup e WHERE e.id = :id"),
    @NamedQuery(name = "EmployeePayGroup.findByVersion", query = "SELECT e FROM EmployeePayGroup e WHERE e.version = :version")})
public class EmployeePayGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "version", nullable = false)
    private int version;
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Employee employeeId;
    @JoinColumn(name = "payGroupId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PayGroup payGroupId;

    public EmployeePayGroup() {
    }

    public EmployeePayGroup(Integer id) {
        this.id = id;
    }

    public EmployeePayGroup(Integer id, int version) {
        this.id = id;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public PayGroup getPayGroupId() {
        return payGroupId;
    }

    public void setPayGroupId(PayGroup payGroupId) {
        this.payGroupId = payGroupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeePayGroup)) {
            return false;
        }
        EmployeePayGroup other = (EmployeePayGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.EmployeePayGroup[id=" + id + "]";
    }

}
