
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.Functie;
import Realdolmen.MyCareer.domain.Werknemer;
import java.util.List;

public interface IWerknemerService {
    public Werknemer findWerknemerById(Long werknemerId);
    
    public List<Functie> findFunctie();
    public List<Functie> findOldFunctie();
    public List<Functie> findCurrentFunctie();
    
    public void deleteOldFunctie();
    public void deleteCurrentFunctie();
}
