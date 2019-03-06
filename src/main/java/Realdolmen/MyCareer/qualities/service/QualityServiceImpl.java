
package Realdolmen.MyCareer.qualities.service;

import Realdolmen.MyCareer.qualities.service.QualityService;
import Realdolmen.MyCareer.qualities.domain.StrongQuality;
import Realdolmen.MyCareer.qualities.domain.StrongQuality;
import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import Realdolmen.MyCareer.qualities.domain.Quality;
//import Realdolmen.MyCareer.repositories.SuperklasseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Realdolmen.MyCareer.qualities.repositories.StrongQualityRepository;
import Realdolmen.MyCareer.qualities.repositories.StrongQualityRepository;
import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import Realdolmen.MyCareer.qualities.repositories.WeakQualityRepository;
import Realdolmen.MyCareer.qualities.repositories.WeakQualityRepository;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QualityServiceImpl implements QualityService<Quality>{

    @Autowired
    private StrongQualityRepository strongQualityRepository;
    
    @Autowired
    private WeakQualityRepository weakQualityRepository;

    @Override
    public List<Quality> findAllStrongQualitiesOfEmployee(Long employeeId) {
        return strongQualityRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Quality> findAllWeakQualitiesOfEmployee(Long employeeId) {
        return weakQualityRepository.findByEmployeeId(employeeId);
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
    public void saveTwoListsOfQualities(List<StrongQuality> strongqualities, List<WeakQuality> weakqualities) {
        strongQualityRepository.saveAll(strongqualities);
        weakQualityRepository.saveAll(weakqualities);
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
    public StrongQuality findStrongQualityById(Long id) {
        return (StrongQuality) strongQualityRepository.findStrongQualityById(id);
    }

    @Override
    public WeakQuality findWeakQualityById(Long id) {
        return (WeakQuality) weakQualityRepository.findWeakQualityById(id);
    }

    

    
}
