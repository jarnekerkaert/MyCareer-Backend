package com.realdolmen.mycareer.employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;

@Entity
class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String email;
    //@NotNull
    private String password;
    //@NotNull
    private Date birthdate;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getFirstname() {
        return firstname;
    }

    void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    String getLastname() {
        return lastname;
    }

    void setLastname(String lastname) {
        this.lastname = lastname;
    }

    Date getBirthdate() {
        return birthdate;
    }

    void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    Employee() { }

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
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Realdolmen.MyCareer.domain.Employee[ id=" + id + " ]";
    }

}
