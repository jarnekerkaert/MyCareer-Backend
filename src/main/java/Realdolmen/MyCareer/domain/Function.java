package Realdolmen.MyCareer.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Function is a superclass, his subclasses are CurrentFunction and PrevFunction
 * @author NWTBN85
 */
@Entity
@Table(name = "function")
public class Function 
        //implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * Each function has a foreign key: employee_id, so each function is linked to exactly one employee_id
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;
    
    /*
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_id_previous")
    private Employee employee2; */
    
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;
    
    @Column(name = "start")
    private Date start;
    @Column(name = "ending")
    private Date ending;
    
     public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

//    public Employee getEmployee() {
//        return employee;
//    }
    
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Function() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Function)) {
            return false;
        }
        Function other = (Function) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Realdolmen.MyCareer.domain.Function[ id=" + id + " ]";
    } 
}