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
@Table(name = "benefitype", catalog = "freehrms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BenefitType.findAll", query = "SELECT b FROM BenefitType b"),
    @NamedQuery(name = "BenefitType.findById", query = "SELECT b FROM BenefitType b WHERE b.id = ?"),
    @NamedQuery(name = "BenefitType.findByName", query = "SELECT b FROM BenefitType b WHERE b.name = ?"),
    @NamedQuery(name = "BenefitType.findByCap", query = "SELECT b FROM BenefitType b WHERE b.cap = ?")})
public class BenefitType implements Serializable {

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
    @Column(name = "cap", nullable = false)
    private double cap;

    public BenefitType() {
    }

    public BenefitType(Integer id) {
        this.id = id;
    }

    public BenefitType(Integer id, String name, double cap) {
        this.id = id;
        this.name = name;
        this.cap = cap;
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

    public double getCap() {
        return cap;
    }

    public void setCap(double cap) {
        this.cap = cap;
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
        if (!(object instanceof BenefitType)) {
            return false;
        }
        BenefitType other = (BenefitType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.BenefiType[ id=" + id + " ]";
    }

}
