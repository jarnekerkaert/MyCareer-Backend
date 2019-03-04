
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.StrongQuality;
import Realdolmen.MyCareer.domain.WeakQuality;
import Realdolmen.MyCareer.domain.Quality;
import java.util.List;

public interface IQualityService<T extends Quality> {
    // get
    public List<StrongQuality> findAllStrongQualitiesOfEmployee(Long employeeId);
    public List<WeakQuality> findAllWeakQualitiesOfEmployee(Long employeeId);
    
    // post
    public Quality save(StrongQuality quality);
    public Quality save(WeakQuality quality);
    public List<StrongQuality> saveListOfStrongQualities(List<StrongQuality> qualities);
    public List<WeakQuality> saveListOfWeakQualities(List<WeakQuality> qualities);
    public String saveTwoListsOfQualities(List<StrongQuality> strongqualities, List<WeakQuality> weakqualities);
    
}
