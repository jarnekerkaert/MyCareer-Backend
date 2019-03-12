
package Realdolmen.MyCareer.qualities.service;

import Realdolmen.MyCareer.qualities.domain.Quality;
import java.util.List;
import java.util.Optional;

public interface QualityService<T extends Quality> {

    // get
    List<Quality> findByEmployeeId(Long employeeId);
    Quality findQualityById(Long id);
    
    List<Quality> findAllStrongQualities(Long employeeId);
    List<Quality> findAllWeakQualities(Long employeeId);
    
    // post
    void saveQualities(List<Quality> qualities);
    
    // delete
    void deleteQuality(Quality quality);

    void deleteByEmployeeId(Long employeeId);
}
