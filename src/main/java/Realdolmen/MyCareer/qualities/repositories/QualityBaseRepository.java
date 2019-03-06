
package Realdolmen.MyCareer.qualities.repositories;

import Realdolmen.MyCareer.qualities.domain.Quality;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QualityBaseRepository<T extends Quality> extends JpaRepository<T, Long>{
    
    public T findQualityById(Long id);
    public List<Quality> findByEmployeeId(Long employeeId);
}
