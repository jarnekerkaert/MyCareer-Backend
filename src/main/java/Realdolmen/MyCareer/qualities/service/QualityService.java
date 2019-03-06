
package Realdolmen.MyCareer.qualities.service;

import Realdolmen.MyCareer.qualities.domain.StrongQuality;
import Realdolmen.MyCareer.qualities.domain.StrongQuality;
import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import Realdolmen.MyCareer.qualities.domain.Quality;
import java.util.List;
import java.util.Optional;

public interface QualityService<T extends Quality> {
    // get
    public List<Quality> findAllStrongQualitiesOfEmployee(Long employeeId);
    public List<Quality> findAllWeakQualitiesOfEmployee(Long employeeId);
    
    public StrongQuality findStrongQualityById(Long userId);
    public WeakQuality findWeakQualityById(Long userId);
    
    // post
    public Quality save(StrongQuality quality);
    public Quality save(WeakQuality quality);
    public List<StrongQuality> saveListOfStrongQualities(List<StrongQuality> qualities);
    public List<WeakQuality> saveListOfWeakQualities(List<WeakQuality> qualities);
    public void saveTwoListsOfQualities(List<StrongQuality> strongqualities, List<WeakQuality> weakqualities);
    
    // delete
    public void deleteStrongQuality(StrongQuality quality);
    public void deleteWeakQuality(WeakQuality quality);
}
