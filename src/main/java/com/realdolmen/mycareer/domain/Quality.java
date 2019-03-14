
package com.realdolmen.mycareer.domain;

import com.realdolmen.mycareer.common.PostgreSQLEnumType;
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

    private Long employeeId;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public QualityType getType() {
        return type;
    }

    public void setType(QualityType type) {
        this.type = type;
    }
    
}
