
package Realdolmen.MyCareer.qualities;

import Realdolmen.MyCareer.qualities.Quality;
import java.util.List;

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
