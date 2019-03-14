package com.realdolmen.mycareer.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "function")
public class Function
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long employeeId;

    private String title;

    private String description;

    private Date start;

    private Date ending;


    Function() { }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
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

    void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    void setStart(Date start) {
        this.start = start;
    }

    public Date getEnding() {
        return ending;
    }

    void setEnding(Date ending) {
        this.ending = ending;
    }

    public boolean isCurrent() {
        return Objects.isNull(getEnding());
    }
}
