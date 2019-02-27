
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.Functie;
import Realdolmen.MyCareer.domain.Werknemer;
import Realdolmen.MyCareer.repositories.WerknemerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WerknemerService implements IWerknemerService {

    @Autowired
    private WerknemerRepository repository;
    
    @Override
    public Werknemer findWerknemerById(Long werknemerId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return repository.findWerknemerById(werknemerId);
    }

    @Override
    public List<Functie> findFunctie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Functie> findOldFunctie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Functie> findCurrentFunctie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOldFunctie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCurrentFunctie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
