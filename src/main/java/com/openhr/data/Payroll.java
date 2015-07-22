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
import javax.persistence.Lob;
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
@Table(name = "payroll", catalog = "freehrms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payroll.findAll", query = "SELECT p FROM Payroll p"),
    @NamedQuery(name = "Payroll.findById", query = "SELECT p FROM Payroll p WHERE p.id = :id"),
    @NamedQuery(name = "Payroll.findByRunOnDate", query = "SELECT p FROM Payroll p WHERE p.runOnDate = :runOnDate")})
public class Payroll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "runOnDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date runOnDate;
    @Basic(optional = false)
    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;
    @JoinColumn(name = "runBy", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Users runBy;

    public Payroll() {
    }

    public Payroll(Integer id) {
        this.id = id;
    }

    public Payroll(Integer id, Date runOnDate, byte[] file) {
        this.id = id;
        this.runOnDate = runOnDate;
        this.file = file;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRunOnDate() {
        return runOnDate;
    }

    public void setRunOnDate(Date runOnDate) {
        this.runOnDate = runOnDate;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Users getRunBy() {
        return runBy;
    }

    public void setRunBy(Users runBy) {
        this.runBy = runBy;
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
        if (!(object instanceof Payroll)) {
            return false;
        }
        Payroll other = (Payroll) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.Payroll[ id=" + id + " ]";
    }

}
