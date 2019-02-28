
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.CurrentFunction;
import Realdolmen.MyCareer.domain.Function;
import java.util.List;
import javax.transaction.Transactional;

@Transactional
public interface CurrentFunctionRepository extends FunctionBaseRepository<CurrentFunction>{
     @Override
     public List<Function> findByEmployee_id(Long employee_id);
}
