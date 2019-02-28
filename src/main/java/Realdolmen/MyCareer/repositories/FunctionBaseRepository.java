
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.Function;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FunctionBaseRepository <T extends Function> extends JpaRepository<T, Long>{
    public T findFunctionById(Long id);
    public List<Function> findByEmployee_id(Long employee_id);
    //public Function save(Function function);
}
