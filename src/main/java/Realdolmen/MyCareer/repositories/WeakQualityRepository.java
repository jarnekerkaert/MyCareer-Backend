
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.Quality;
import Realdolmen.MyCareer.domain.WeakQuality;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface WeakQualityRepository extends QualityBaseRepository<WeakQuality>{
    @Override
    public List<Quality> findByEmployee_id(Long employee_id);
}
