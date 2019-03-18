package com.realdolmen.mycareer.functions;

import com.realdolmen.mycareer.employees.Employee;

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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employeeId")
    private Employee employee;
    
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;
    
    @Column(name = "start")
    private Date start;
    @Column(name = "ending")
    private Date ending;

    Function() { }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    boolean isCurrent() {
        return Objects.isNull(getEnding());
    }


}
