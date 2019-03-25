
package com.realdolmen.quality.Qualities;

import com.realdolmen.mycareer.common.QualityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class QualityService {

    private final QualityRepository qualityRepository;

    @Autowired
    QualityService(QualityRepository qualityRepository) {
        this.qualityRepository = qualityRepository;
    }

    List<Quality> findAllStrongQualities(Long employeeId) {
        return qualityRepository.findByEmployeeId(employeeId).stream().filter(f -> f.getType() == QualityType.STRONG).collect(Collectors.toList());
    }

    List<Quality> findAllWeakQualities(Long employeeId) {
        return qualityRepository.findByEmployeeId(employeeId).stream().filter(f -> f.getType() == QualityType.WEAK).collect(Collectors.toList());
    }

    List<Quality> findByEmployeeId(Long employeeId) {
        return qualityRepository.findByEmployeeId(employeeId);
    }

    @Transactional
    void saveQualities(List<Quality> qualities) {
        qualityRepository.saveAll(qualities);
    }

    void deleteQuality(Quality quality) {
        qualityRepository.delete(quality);
    }

    @Transactional
    void deleteByEmployeeId(Long employeeId) {
        qualityRepository.deleteByEmployeeId(employeeId);
    }

    Optional<Quality> findQualityById(Long id) {
        return qualityRepository.findQualityById(id);
    }


}
