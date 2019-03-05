
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
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QualityService implements IQualityService<Quality>{

    @Autowired
    private StrongQualityRepository strongQualityRepository;
    
    @Autowired
    private WeakQualityRepository weakQualityRepository;

    @Override
    public List<Quality> findAllStrongQualitiesOfEmployee(Long employeeId) {
        return strongQualityRepository.findByEmployee_id(employeeId);
    }

    @Override
    public List<Quality> findAllWeakQualitiesOfEmployee(Long employeeId) {
        return weakQualityRepository.findByEmployee_id(employeeId);
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
    @Transactional
    public String saveTwoListsOfQualities(List<StrongQuality> strongqualities, List<WeakQuality> weakqualities) {
        strongQualityRepository.saveAll(strongqualities);
        weakQualityRepository.saveAll(weakqualities);
        return "success";
    }

    @Override
    public void deleteStrongQuality(StrongQuality quality) {
        strongQualityRepository.delete(quality);
    }

    @Override
    public void deleteWeakQuality(WeakQuality quality) {
        weakQualityRepository.delete(quality);
    }

    @Override
    public Optional<StrongQuality> findStrongQualityById(Long id) {
        return strongQualityRepository.findById(id);
    }

    @Override
    public Optional<WeakQuality> findWeakQualityById(Long id) {
        return weakQualityRepository.findById(id);
    }

    

    
}
