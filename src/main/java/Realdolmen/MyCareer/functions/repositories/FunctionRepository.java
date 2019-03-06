
package Realdolmen.MyCareer.functions.repositories;


import Realdolmen.MyCareer.functions.domain.Function;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long>{
    List<Function> findAll();
    List<Function> findByEmployeeId(Long employeeId);
}
