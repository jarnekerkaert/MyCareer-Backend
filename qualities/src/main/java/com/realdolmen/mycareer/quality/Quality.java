
package com.realdolmen.mycareer.quality;

import com.realdolmen.mycareer.common.QualityType;
import com.realdolmen.mycareer.common.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@TypeDef(
    name = "pgsql_enum",
    typeClass = PostgreSQLEnumType.class
)
class Quality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long employeeId;
    @NotNull
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum" )
    private QualityType type;

     Quality() { }

     String getDescription() {
        return description;
    }

     void setDescription(String description) {
        this.description = description;
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

     QualityType getType() {
        return type;
    }

     void setType(QualityType type) {
        this.type = type;
    }
    
}
