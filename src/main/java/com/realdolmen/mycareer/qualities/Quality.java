
package com.realdolmen.mycareer.qualities;

import com.realdolmen.mycareer.common.PostgreSQLEnumType;
import com.realdolmen.mycareer.employees.Employee;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
@Entity
@TypeDef(
    name = "pgsql_enum",
    typeClass = PostgreSQLEnumType.class
)
public
class Quality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employeeId")
    private Employee employee;

    private String description;
    
    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum" )
    private QualityType type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public QualityType getType() {
        return type;
    }

    public void setType(QualityType type) {
        this.type = type;
    }
    
}
