package com.realdolmen.mycareer.common.domain;

import com.realdolmen.mycareer.ambitions.Term;
import com.realdolmen.mycareer.common.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@Entity
public class Ambition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Long employeeId;
    private String title;
    private String motivation;

    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum" )
    private Term term;

//    @ManyToMany
//    @JoinTable(
//            name = "ambition_enabler",
//            joinColumns = @JoinColumn(name = "ambition_id"),
//            inverseJoinColumns = @JoinColumn(name = "enabler_id")
//    )
//    private List<Enabler> enablers = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
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
}
