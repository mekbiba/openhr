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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xmen
 */
@Entity
@Table(name = "tbl_leave", catalog = "freehrms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leave.findAll", query = "SELECT l FROM Leave l"),
    @NamedQuery(name = "Leave.findById", query = "SELECT l FROM Leave l WHERE l.id = :id"),
    @NamedQuery(name = "Leave.findByUnuseddays", query = "SELECT l FROM Leave l WHERE l.unuseddays = :unuseddays"),
    @NamedQuery(name = "Leave.findByYear", query = "SELECT l FROM Leave l WHERE l.year = :year")})
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "unuseddays", nullable = false)
    private int unuseddays;
    @Basic(optional = false)
    @Column(name = "year", nullable = false)
    private int year;
    @JoinColumn(name = "leaveType", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LeaveType leaveType;
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Employee employeeId;

    public Leave() {
    }

    public Leave(Integer id) {
        this.id = id;
    }

    public Leave(Integer id, int unuseddays, int year) {
        this.id = id;
        this.unuseddays = unuseddays;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUnuseddays() {
        return unuseddays;
    }

    public void setUnuseddays(int unuseddays) {
        this.unuseddays = unuseddays;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof Leave)) {
            return false;
        }
        Leave other = (Leave) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.Leave[ id=" + id + " ]";
    }

}
