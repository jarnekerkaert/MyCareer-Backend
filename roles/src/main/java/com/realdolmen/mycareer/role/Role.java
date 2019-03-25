package com.realdolmen.mycareer.role;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;

@Entity
class Role {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long employeeId;
    @NotNull
    private String title;
    @NotNull
    private String description;

    private Date start;

    private Date ending;

    Role() {
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    Long getEmployeeId() {
        return employeeId;
    }

    void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    Date getStart() {
        return start;
    }

    void setStart(Date start) {
        this.start = start;
    }

    Date getEnding() {
        return ending;
    }

    void setEnding(Date ending) {
        this.ending = ending;
    }

    boolean isCurrent() {
        return Objects.isNull(getEnding());
    }
}
