
package com.realdolmen.mycareer.qualities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QualityService {

    private final QualityRepository qualityRepository;

    @Autowired
    public QualityService(QualityRepository qualityRepository) {
        this.qualityRepository = qualityRepository;
    }

    public List<Quality> findAllStrongQualities(Long employeeId) {
        return qualityRepository.findByEmployeeId(employeeId).stream().filter(f -> f.getType() == QualityType.STRONG).collect(Collectors.toList());
    }

    public List<Quality> findAllWeakQualities(Long employeeId) {
        return qualityRepository.findByEmployeeId(employeeId).stream().filter(f -> f.getType() == QualityType.WEAK).collect(Collectors.toList());
    }

    List<Quality> findByEmployeeId(Long employeeId) {
        return qualityRepository.findByEmployeeId(employeeId);
    }

    @Transactional
    public void saveQualities(List<Quality> qualities) {
        qualityRepository.saveAll(qualities);
    }

    public void deleteQuality(Quality quality) {
        qualityRepository.delete(quality);
    }

    @Transactional
    void deleteByEmployeeId(Long employeeId) {
        qualityRepository.deleteByEmployeeId(employeeId);
    }

    public Optional<Quality> findQualityById(Long id) {
        return qualityRepository.findQualityById(id);
    }


}
