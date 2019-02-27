package Realdolmen.MyCareer.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "functie")
public class Functie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "inhoud", nullable = false)
    private String inhoud;
    @Column(name = "van", nullable = false)
    private Date van;
    @Column(name = "tot", nullable = false)
    private Date tot;
    @Column(name = "beschrijving", nullable = false)
    private Date beschrijving;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public Date getVan() {
        return van;
    }

    public void setVan(Date van) {
        this.van = van;
    }

    public Date getTot() {
        return tot;
    }

    public void setTot(Date tot) {
        this.tot = tot;
    }

    public Date getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(Date beschrijving) {
        this.beschrijving = beschrijving;
    }
    
    

    public Functie() {
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
        if (!(object instanceof Functie)) {
            return false;
        }
        Functie other = (Functie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Realdolmen.MyCareer.domain.Functie[ id=" + id + " ]";
    }
}
