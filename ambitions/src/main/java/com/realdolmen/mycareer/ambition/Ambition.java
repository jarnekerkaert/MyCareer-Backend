package com.realdolmen.mycareer.ambition;

import com.realdolmen.mycareer.common.Term;
import com.realdolmen.mycareer.common.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@Entity
class Ambition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private Long employeeId;
    @NotNull
    private String title;
    @NotNull
    private String motivation;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Term term;

     Ambition() {
    }

//    @ManyToMany
//    @JoinTable(
//            name = "ambition_enabler",
//            joinColumns = @JoinColumn(name = "ambition_id"),
//            inverseJoinColumns = @JoinColumn(name = "enabler_id")
//    )
//    private List<Enabler> enablers = new ArrayList<>();
     long getId() {
        return id;
    }

     void setId(long id) {
        this.id = id;
    }

     Long getEmployeeId() {
        return employeeId;
    }

     void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

     Term getTerm() {
        return term;
    }

     void setTerm(Term term) {
        this.term = term;
    }

     String getTitle() {
        return title;
    }

     void setTitle(String title) {
        this.title = title;
    }

     String getMotivation() {
        return motivation;
    }

     void setMotivation(String motivation) {
        this.motivation = motivation;
    }
}
