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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xmen
 */
@Entity
@Table(name = "deductiontype", catalog = "freehrms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeductionType.findAll", query = "SELECT d FROM DeductionType d"),
    @NamedQuery(name = "DeductionType.findById", query = "SELECT d FROM DeductionType d WHERE d.id = :id"),
    @NamedQuery(name = "DeductionType.findByName", query = "SELECT d FROM DeductionType d WHERE d.name = :name")})
public class DeductionType implements Serializable {
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "deductionType")
     private List<Deduction> deductionList;*/

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
    @Lob
    @Column(name = "description", nullable = false, length = 65535)
    private String description;

    public DeductionType() {
    }

    public DeductionType(Integer id) {
        this.id = id;
    }

    public DeductionType(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof DeductionType)) {
            return false;
        }
        DeductionType other = (DeductionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.DeductionType[ id=" + id + " ]";
    }

    /*@XmlTransient
     public List<Deduction> getDeductionList() {
     return deductionList;
     }

     public void setDeductionList(List<Deduction> deductionList) {
     this.deductionList = deductionList;
     }
     */
}
