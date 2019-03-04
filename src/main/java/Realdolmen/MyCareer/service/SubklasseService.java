
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.Subklasse1;
import Realdolmen.MyCareer.domain.Subklasse2;
import Realdolmen.MyCareer.domain.Superklasse;
import Realdolmen.MyCareer.repositories.Subklasse1Repository;
import Realdolmen.MyCareer.repositories.Subklasse2Repository;
//import Realdolmen.MyCareer.repositories.SuperklasseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubklasseService implements ISubklasseService<Superklasse>{

    @Autowired
    private Subklasse1Repository repository1;
    
    @Autowired
    private Subklasse2Repository repository2;
    
    @Override
    public Superklasse findSubklasseById(Long id) {
        return repository1.findSubklasseById(id);
    }

    @Override
    public List<Subklasse1> findAll1() {
        return repository1.findAll();
    }

    @Override
    public List<Subklasse2> findAll2() {
        return repository2.findAll();
    }
    
}
