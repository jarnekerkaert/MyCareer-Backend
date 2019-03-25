package com.realdolmen.mycareer.CommonLibrary.common.dto;

import com.realdolmen.mycareer.CommonLibrary.common.Term;

public class AmbitionModel {
    private Long id;
    private Long employeeId;
    private String title;
    private String motivation;
    private Term term;

    public AmbitionModel() {
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

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
    
    
}
