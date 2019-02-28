
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.Superklasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SuperklasseBaseRepository<T extends Superklasse> extends JpaRepository<T, Long>{
    
    public T findSubklasseById(Long id);
}
