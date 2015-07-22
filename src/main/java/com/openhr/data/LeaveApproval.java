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
@Table(name = "leaveapproval", catalog = "freehrms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveApproval.findAll", query = "SELECT l FROM LeaveApproval l"),
    @NamedQuery(name = "LeaveApproval.findById", query = "SELECT l FROM LeaveApproval l WHERE l.id = ?"),
    @NamedQuery(name = "LeaveApproval.findByApprovedbydate", query = "SELECT l FROM LeaveApproval l WHERE l.approvedbydate = ?")})
public class LeaveApproval implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "approvedbydate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedbydate;
    @JoinColumn(name = "requestId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private LeaveRequest requestId;
    @JoinColumn(name = "approverId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Employee approverId;

    public LeaveApproval() {
    }

    public LeaveApproval(Integer id) {
        this.id = id;
    }

    public LeaveApproval(Integer id, Date approvedbydate) {
        this.id = id;
        this.approvedbydate = approvedbydate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getApprovedbydate() {
        return approvedbydate;
    }

    public void setApprovedbydate(Date approvedbydate) {
        this.approvedbydate = approvedbydate;
    }

    public LeaveRequest getRequestId() {
        return requestId;
    }

    public void setRequestId(LeaveRequest requestId) {
        this.requestId = requestId;
    }

    public Employee getApproverId() {
        return approverId;
    }

    public void setApproverId(Employee approverId) {
        this.approverId = approverId;
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
        if (!(object instanceof LeaveApproval)) {
            return false;
        }
        LeaveApproval other = (LeaveApproval) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.LeaveApproval[ id=" + id + " ]";
    }

}
