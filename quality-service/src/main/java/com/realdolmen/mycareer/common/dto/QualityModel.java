package com.realdolmen.mycareer.common.dto;


import com.realdolmen.mycareer.common.QualityType;

public class QualityModel {

    private Long id;
    private Long employeeId;
    private String description;
    private QualityType type;

    public QualityModel() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QualityType getType() {
        return type;
    }

    public void setType(QualityType type) {
        this.type = type;
    }

    
}
