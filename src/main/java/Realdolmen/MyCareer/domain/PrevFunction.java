
package Realdolmen.MyCareer.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "prevfunction")
public class PrevFunction extends Function{
    
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
}
