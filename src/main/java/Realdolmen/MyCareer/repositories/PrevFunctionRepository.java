
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.PrevFunction;
import java.util.List;
import javax.transaction.Transactional;

@Transactional
public interface PrevFunctionRepository extends FunctionBaseRepository<PrevFunction>{
    @Override
    public List<Function> findByEmployee_id(Long employee_id);
}
