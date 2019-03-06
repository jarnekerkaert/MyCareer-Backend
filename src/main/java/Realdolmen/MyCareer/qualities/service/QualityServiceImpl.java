
package Realdolmen.MyCareer.qualities.service;

import Realdolmen.MyCareer.qualities.service.QualityService;
//import Realdolmen.MyCareer.qualities.domain.StrongQuality;
//import Realdolmen.MyCareer.qualities.domain.StrongQuality;
//import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import Realdolmen.MyCareer.qualities.domain.Quality;
import Realdolmen.MyCareer.qualities.domain.QualityType;
//import Realdolmen.MyCareer.repositories.SuperklasseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import Realdolmen.MyCareer.qualities.repositories.StrongQualityRepository;
//import Realdolmen.MyCareer.qualities.repositories.StrongQualityRepository;
//import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import Realdolmen.MyCareer.qualities.repositories.QualityRepository;
//import Realdolmen.MyCareer.qualities.repositories.WeakQualityRepository;
//import Realdolmen.MyCareer.qualities.repositories.WeakQualityRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QualityServiceImpl implements QualityService<Quality>{

    /*
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
    */
    
    @Autowired
    private QualityRepository qualityRepository;

    @Override
    public List<Quality> findAllStrongQualities(Long employeeId) {
//         return qualityRepository.findByEmployeeId(employeeId).stream().filter( f -> f.getType()== QualityType.STRONG).collect(Collectors.toList());
        return qualityRepository.findByEmployeeId(employeeId).stream().filter( f -> f.getType()== "STRONG").collect(Collectors.toList());
    }

    @Override
    public List<Quality> findAllWeakQualities(Long employeeId) {
//        return qualityRepository.findByEmployeeId(employeeId).stream().filter( f -> f.getType()== QualityType.WEAK).collect(Collectors.toList());
        return qualityRepository.findByEmployeeId(employeeId).stream().filter( f -> f.getType()== "WEAK").collect(Collectors.toList());
    }

    @Override
    public List<Quality> findByEmployeeId(Long employeeId) {
        return qualityRepository.findByEmployeeId(employeeId);
    }

    @Transactional
    @Override
    public void saveQualities(List<Quality> qualities) {
        qualityRepository.saveAll(qualities);
    }

    @Override
    public void deleteQuality(Quality quality) {
        qualityRepository.delete(quality);
    }

    @Override
    public Quality findQualityById(Long id) {
        return qualityRepository.findQualityById(id);
    }
    
    
    
}
