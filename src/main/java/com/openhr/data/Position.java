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
 * @author Mekbib
 */
@Entity
@Table(name = "position", catalog = "freehrms", schema = "")
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "FROM Position p"),
    @NamedQuery(name = "Position.findById", query = "FROM Position p WHERE id = ?"),
    @NamedQuery(name = "Position.findByName", query = "SELECT p FROM Position p WHERE p.name = :name"),
    @NamedQuery(name = "Position.findBySalary", query = "SELECT p FROM Position p WHERE p.salary = :salary"),
    @NamedQuery(name = "Position.findByRaisePerYear", query = "SELECT p FROM Position p WHERE p.raisePerYear = :raisePerYear")})
public class Position implements Serializable {

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
    @Column(name = "salary", nullable = false)
    private double salary;
    @Basic(optional = false)
    @Column(name = "raisePerYear", nullable = false)
    private double raisePerYear;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "positionId")
    //private List<Employee> employeeCollection;

    public Position() {
    }

    public Position(Integer id) {
        this.id = id;
    }

    public Position(Integer id, String name, double salary, double raisePerYear) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.raisePerYear = raisePerYear;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getRaisePerYear() {
        return raisePerYear;
    }

    public void setRaisePerYear(double raisePerYear) {
        this.raisePerYear = raisePerYear;
    }
    /*
     public List<Employee> getEmployeeCollection() {
     return employeeCollection;
     }

     public void setEmployeeCollection(List<Employee> employeeCollection) {
     this.employeeCollection = employeeCollection;
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
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.Position[id=" + id + "]";
    }

}
