package com.realdolmen.mycareer.common.dto;

import com.realdolmen.mycareer.domain.Ambition;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Role;
import com.realdolmen.mycareer.domain.Quality;

import java.util.Date;
import java.util.List;

public class EmployeeModel {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
    private List<Role> roles;
    private List<Quality> qualities;
    private List<Ambition> ambitions;

    public EmployeeModel() {

    }

    public EmployeeModel(Employee employee) {
        this.id = employee.getId();
        this.firstname = employee.getFirstname();
        this.lastname = employee.getLastname();
        this.email = employee.getEmail();
        this.birthdate = employee.getBirthdate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Quality> getQualities() {
        return qualities;
    }

    public void setQualities(List<Quality> qualities) {
        this.qualities = qualities;
    }

    public List<Ambition> getAmbitions() {
        return ambitions;
    }

    public void setAmbitions(List<Ambition> ambitions) {
        this.ambitions = ambitions;
    }
}
