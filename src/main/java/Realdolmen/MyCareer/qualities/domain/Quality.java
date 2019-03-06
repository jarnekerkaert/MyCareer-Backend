
package Realdolmen.MyCareer.qualities.domain;

import Realdolmen.MyCareer.employees.domain.Employee;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
// later te veranderen naar Punt
@Entity
@Table(name = "quality")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@MappedSuperclass
public class Quality 
      //  implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employeeId")
    private Employee employee;
    
    @Column(name = "description")
    private String description;
    
    //@Enumerated(EnumType.STRING)
    @Column(name = "type")
    //private QualityType type;
    private String type;

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

//    public QualityType getType() {
//        return type;
//    }
//
//    public void setType(QualityType type) {
//        this.type = type;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
