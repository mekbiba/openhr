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
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mekbib
 */
@Entity
@Table(name = "employee", catalog = "freehrms", schema = "")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e WHERE upper(e.status)='ACTIVE'"),
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = ?"),
    @NamedQuery(name = "Employee.findByEmployeeId", query = "SELECT e FROM Employee e WHERE e.employeeId = ?"),
    @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = ?"),
    @NamedQuery(name = "Employee.findByMiddlename", query = "SELECT e FROM Employee e WHERE e.middlename = ?"),
    @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = ?"),
    @NamedQuery(name = "Employee.findBySex", query = "SELECT e FROM Employee e WHERE e.sex = ?"),
    @NamedQuery(name = "Employee.findByBirthdate", query = "SELECT e FROM Employee e WHERE e.birthdate = ?"),
    @NamedQuery(name = "Employee.findByHiredate", query = "SELECT e FROM Employee e WHERE e.hiredate = ?")})
@NamedNativeQuery(name = "Employee.findLastId", query = "SELECT * FROM Employee WHERE Employee.id = (SELECT max(Employee.id) FROM EMPLOYEE)",
        resultClass = Employee.class)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "employeeId", nullable = false, length = 45)
    private String employeeId;
    @Basic(optional = false)
    @Column(name = "firstname", nullable = false, length = 45)
    private String firstname;
    @Basic(optional = false)
    @Column(name = "middlename", nullable = false, length = 45)
    private String middlename;
    @Basic(optional = false)
    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;
    @Basic(optional = false)
    @Column(name = "sex", nullable = false, length = 6)
    private String sex;

    @Basic(optional = false)
    @Column(name = "photo_path", nullable = false, length = 55)
    private String photo;

    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Basic(optional = false)
    @Column(name = "birthdate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthdate;
    @Basic(optional = false)
    @Column(name = "hiredate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date hiredate;
    /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
     private List<Users> usersCollection;
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
     private List<Leave> leaveCollection;*/
    @JoinColumn(name = "positionId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Position positionId;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Employee(Integer id, String employeeId, String firstname, String middlename, String lastname, String sex, Date birthdate, Date hiredate) {
        this.id = id;
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.sex = sex;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getBirthdate() {
        return birthdate.getTime();
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public long getHiredate() {
        return hiredate.getTime();
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    /*public List<Users> getUsersCollection() {
     return usersCollection;
     }

     public void setUsersCollection(List<Users> usersCollection) {
     this.usersCollection = usersCollection;
     }

     public List<Leave> getLeaveCollection() {
     return leaveCollection;
     }

     public void setLeaveCollection(List<Leave> leaveCollection) {
     this.leaveCollection = leaveCollection;
     }*/
    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.openhr.data.Employee[id=" + id + "]";
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
