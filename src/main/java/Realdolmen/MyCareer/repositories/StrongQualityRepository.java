
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.Quality;
import Realdolmen.MyCareer.domain.StrongQuality;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface StrongQualityRepository extends QualityBaseRepository<StrongQuality>{
    @Override
    public List<Quality> findByEmployeeId(Long employeeId);
    public StrongQuality findStrongQualityById(Long id);
}
