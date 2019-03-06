
package Realdolmen.MyCareer.qualities.service;

import Realdolmen.MyCareer.qualities.domain.Quality;
import java.util.List;
import java.util.Optional;

public interface QualityService<T extends Quality> {

    // get
    public List<Quality> findByEmployeeId(Long employeeId);
    public Quality findQualityById(Long id);
    
    public List<Quality> findAllStrongQualities(Long employeeId);
    public List<Quality> findAllWeakQualities(Long employeeId);
    
    // post
    public void saveQualities(List<Quality> qualities);
    
    // delete
    public void deleteQuality(Quality quality);
}
