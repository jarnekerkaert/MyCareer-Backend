
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.Quality;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QualityBaseRepository<T extends Quality> extends JpaRepository<T, Long>{
    
    public T findQualityById(Long id);
    public List<Quality> findByEmployee_id(Long employee_id);
}
