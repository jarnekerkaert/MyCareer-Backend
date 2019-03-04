
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.StrongQuality;
import Realdolmen.MyCareer.domain.WeakQuality;
import Realdolmen.MyCareer.domain.Quality;
//import Realdolmen.MyCareer.repositories.SuperklasseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Realdolmen.MyCareer.repositories.StrongQualityRepository;
import Realdolmen.MyCareer.repositories.WeakQualityRepository;

@Service
public class QualityService implements IQualityService<Quality>{

    @Autowired
    private StrongQualityRepository strongQualityRepository;
    
    @Autowired
    private WeakQualityRepository weakQualityRepository;

    @Override
    public List<StrongQuality> findAllStrongQualitiesOfEmployee(Long employeeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WeakQuality> findAllWeakQualitiesOfEmployee(Long employeeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Quality save(StrongQuality quality) {
        return strongQualityRepository.save(quality);
    }

    @Override
    public Quality save(WeakQuality quality) {
        return weakQualityRepository.save(quality);
    }

    @Override
    public List<StrongQuality> saveListOfStrongQualities(List<StrongQuality> qualities) {
        return strongQualityRepository.saveAll(qualities);
    }

    @Override
    public List<WeakQuality> saveListOfWeakQualities(List<WeakQuality> qualities) {
        return weakQualityRepository.saveAll(qualities);
    }

    @Override
    public String saveTwoListsOfQualities(List<StrongQuality> strongqualities, List<WeakQuality> weakqualities) {
        strongQualityRepository.saveAll(strongqualities);
        weakQualityRepository.saveAll(weakqualities);
        return "success";
    }

    

    
}
