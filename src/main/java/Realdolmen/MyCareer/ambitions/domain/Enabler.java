package Realdolmen.MyCareer.ambitions.domain;

import Realdolmen.MyCareer.common.PostgreSQLEnumType;
import Realdolmen.MyCareer.employees.domain.Employee;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
@Entity
public class Enabler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//
//    @ManyToMany(mappedBy = "enablers")
//    private List<Ambition> ambition = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum" )
    private EnablerType type;

    private String title;
    private String description;
    private String url;
    private String filepath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
//
//    public List<Ambition> getAmbition() {
//        return ambition;
//    }
//
//    public void setAmbition(List<Ambition> ambition) {
//        this.ambition = ambition;
//    }

    public EnablerType getType() {
        return type;
    }

    public void setType(EnablerType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
