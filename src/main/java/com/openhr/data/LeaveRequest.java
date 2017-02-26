/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.data;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xmen
 */
@Entity
@Table(name = "leaverequest", catalog = "freehrms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveRequest.findAll", query = "SELECT l FROM LeaveRequest l"),
    @NamedQuery(name = "LeaveRequest.findById", query = "SELECT l FROM LeaveRequest l WHERE l.id = :id"),
    @NamedQuery(name = "LeaveRequest.findByLeaveDate", query = "SELECT l FROM LeaveRequest l WHERE l.leaveDate = :leaveDate"),
    @NamedQuery(name = "LeaveRequest.findByReturnDate", query = "SELECT l FROM LeaveRequest l WHERE l.returnDate = :returnDate"),
    @NamedQuery(name = "LeaveRequest.findByStatus", query = "SELECT l FROM LeaveRequest l WHERE l.status = :status"),
    @NamedQuery(name = "LeaveRequest.findByNoOfDays", query = "SELECT l FROM LeaveRequest l WHERE l.noOfDays = :noOfDays")})
public class LeaveRequest implements Serializable {

    @Basic(optional = false)
    @Column(name = "leaveDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDate;
    @Basic(optional = false)
    @Column(name = "returnDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "requestId")
     private List<LeaveApproval> leaveApprovalList;*/
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    private int status;

    @Basic(optional = false)
    @Column(name = "noOfDays", nullable = false)
    private int noOfDays;

    @Basic(optional = false)
    @Column(name = "description", nullable = false)
    private String description;

    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Employee employeeId;
    @JoinColumn(name = "leaveTypeId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LeaveType leaveTypeId;

    public LeaveRequest() {
    }

    public LeaveRequest(Integer id) {
        this.id = id;
    }

    public LeaveRequest(Integer id, Date leaveDate, Date returnDate, int status) {
        this.id = id;
        this.leaveDate = leaveDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveType getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(LeaveType leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
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
        if (!(object instanceof LeaveRequest)) {
            return false;
        }
        LeaveRequest other = (LeaveRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.LeaveRequest[ id=" + id + " ]";
    }

    public long getLeaveDate() {
        return leaveDate.getTime();
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public long getReturnDate() {
        return returnDate.getTime();
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
     @XmlTransient
     public List<LeaveApproval> getLeaveApprovalList() {
     return leaveApprovalList;
     }

     public void setLeaveApprovalList(List<LeaveApproval> leaveApprovalList) {
     this.leaveApprovalList = leaveApprovalList;
     }*/
}
