package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.ambitions.Ambition;
import com.realdolmen.mycareer.functions.Function;
import com.realdolmen.mycareer.qualities.Quality;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Employee has a list of functions
     * Function is an abstract class, his subclasses are CurrentFunction and PrevFunction
     * Technically an employee has two lists of functions: current functions and previous functions
     * We work with inheritance, so we are using one list of functions
     */
    @OneToMany(mappedBy = "employee")
    private List<Function> functions = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Quality> qualities = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Ambition> ambitions = new ArrayList<>();
    /*
     @OneToMany(mappedBy = "employee2")
    private List<Function> previous_functions =  new ArrayList<>(); */

    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;

    //@NotEmpty
    //@Email(message = "Email moet een geldig realdolmen email zijn!")
    @Column(name = "email", nullable = false)
    private String email;

    //@NotEmpty
    //@Size(min = 4, max = 20)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "cv_filepath")
    private String cv_filepath;

    @Column(name = "birthdate")
    private Date birthdate;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee() {
    }

    public Employee(Employee e) {
        this.setId(e.getId());
        this.setEmail(e.getEmail());
        this.setBirthdate(e.getBirthdate());
        this.setFirstname(e.getFirstname());
        this.setLastname(e.getLastname());
        this.setPassword(e.getPassword());
        this.setCv_filepath(e.getCv_filepath());
        this.setAmbitions(e.getAmbitions());
        this.setFunctions(e.getFunctions());
        this.setQualities(e.getQualities());
    }

    public void addFunction(Function f) {
        f.setEmployee(this);
        functions.add(f);
    }


    public void addFunctions(List<Function> f) {
        f.forEach(x -> x.setEmployee(this));
        functions.addAll(f);
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        functions.forEach(x -> x.setEmployee(this));
        this.functions = functions;
    }

    public List<Quality> getQualities() {
        return qualities;
    }

    public void setQualities(List<Quality> qualities) {
        qualities.forEach(x -> x.setEmployee(this));
        this.qualities = qualities;
    }

    public void addQualities(List<Quality> q) {
        q.forEach(x -> x.setEmployee(this));
        qualities.addAll(q);
    }

    public List<Ambition> getAmbitions() {
        return ambitions;
    }

    public void setAmbitions(List<Ambition> ambitions) {
        ambitions.forEach(x -> x.setEmployee(this));
        this.ambitions = ambitions;
    }

    public void addAmbition(Ambition a) {
        a.setEmployee(this);
        ambitions.add(a);
    }

    public void addAmbitions(List<Ambition> a) {
        a.forEach(x -> x.setEmployee(this));
        ambitions.addAll(a);
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
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Realdolmen.MyCareer.domain.Employee[ id=" + id + " ]";
    }

}
