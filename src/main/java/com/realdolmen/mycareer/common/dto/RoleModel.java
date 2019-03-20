package com.realdolmen.mycareer.common.dto;

import com.realdolmen.mycareer.roles.Role;
import java.util.Date;

public class RoleModel {

    private Long id;
    private Long employeeId;
    private String title;
    private String description;
    private Date start;
    private Date ending;

    public RoleModel() {
    }

    public RoleModel(Role role) {
        this.id = role.getId();
        this.employeeId = role.getEmployeeId();
        this.title = role.getTitle();
        this.description = role.getDescription();
        this.start = role.getStart();
        this.ending = role.getEnding();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }
    
    
}
