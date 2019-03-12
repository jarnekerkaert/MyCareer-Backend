
package Realdolmen.MyCareer.qualities.service;

import Realdolmen.MyCareer.qualities.domain.Quality;
import Realdolmen.MyCareer.qualities.domain.QualityType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Realdolmen.MyCareer.qualities.repositories.QualityRepository;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QualityServiceImpl implements QualityService<Quality>{
    
    @Autowired
    private QualityRepository qualityRepository;

    @Override
    public List<Quality> findAllStrongQualities(Long employeeId) {
         return qualityRepository.findByEmployeeId(employeeId).stream().filter( f -> f.getType()== QualityType.STRONG).collect(Collectors.toList());
//        return qualityRepository.findByEmployeeId(employeeId).stream().filter( f -> f.getType().equals("STRONG")).collect(Collectors.toList());
    }

    @Override
    public List<Quality> findAllWeakQualities(Long employeeId) {
        return qualityRepository.findByEmployeeId(employeeId).stream().filter( f -> f.getType()== QualityType.WEAK).collect(Collectors.toList());
//        return qualityRepository.findByEmployeeId(employeeId).stream().filter( f -> f.getType().equals("WEAK")).collect(Collectors.toList());
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

    @Transactional
    @Override
    public void deleteByEmployeeId(Long employeeId) {
        qualityRepository.deleteByEmployeeId(employeeId);
    }

    @Override
    public Quality findQualityById(Long id) {
        return qualityRepository.findQualityById(id);
    }


}
