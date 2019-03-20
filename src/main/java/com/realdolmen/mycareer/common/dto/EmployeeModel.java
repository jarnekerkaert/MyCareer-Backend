package com.realdolmen.mycareer.common.dto;

import com.realdolmen.mycareer.ambitions.Ambition;
import com.realdolmen.mycareer.employees.Employee;
import com.realdolmen.mycareer.roles.Role;
import com.realdolmen.mycareer.qualities.Quality;

import java.util.Date;
import java.util.List;

public class EmployeeModel {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String cv_filepath;
    private Date birthdate;
    private List<RoleModel> roles;
    private List<QualityModel> qualities;
    private List<AmbitionModel> ambitions;
    //private List<Ambition> ambitions;

    public EmployeeModel() {

    }

    public EmployeeModel(Employee employee) {
        this.id = employee.getId();
        this.firstname = employee.getFirstname();
        this.lastname = employee.getLastname();
        this.email = employee.getEmail();
        this.cv_filepath = employee.getCv_filepath();
        this.birthdate = employee.getBirthdate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCv_filepath() {
        return cv_filepath;
    }

    public void setCv_filepath(String cv_filepath) {
        this.cv_filepath = cv_filepath;
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

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }

    public List<QualityModel> getQualities() {
        return qualities;
    }

    public void setQualities(List<QualityModel> qualities) {
        this.qualities = qualities;
    }

    public List<AmbitionModel> getAmbitions() {
        return ambitions;
    }

    public void setAmbitions(List<AmbitionModel> ambitions) {
        this.ambitions = ambitions;
    }

//    public List<Ambition> getAmbitions() {
//        return ambitions;
//    }
//
//    public void setAmbitions(List<Ambition> ambitions) {
//        this.ambitions = ambitions;
//    }
    
    
}
