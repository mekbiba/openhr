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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xmen
 */
@Entity
@Table(name = "leavetype", catalog = "freehrms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveType.findAll", query = "SELECT l FROM LeaveType l"),
    @NamedQuery(name = "LeaveType.findById", query = "SELECT l FROM LeaveType l WHERE l.id = ?"),
    @NamedQuery(name = "LeaveType.findByName", query = "SELECT l FROM LeaveType l WHERE l.name = ?")})
public class LeaveType implements Serializable {

    @Basic(optional = false)
    @Column(name = "day_cap", nullable = false)
    private double dayCap;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "leaveType")
     private List<Leave> leaveList;*/
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public LeaveType() {
    }

    public LeaveType(Integer id) {
        this.id = id;
    }

    public LeaveType(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveType)) {
            return false;
        }
        LeaveType other = (LeaveType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.LeaveType[ id=" + id + " ]";
    }

    public double getDayCap() {
        return dayCap;
    }

    public void setDayCap(double dayCap) {
        this.dayCap = dayCap;
    }
    /*
     @XmlTransient
     public List<Leave> getLeaveList() {
     return leaveList;
     }

     public void setLeaveList(List<Leave> leaveList) {
     this.leaveList = leaveList;
     }*/

}
