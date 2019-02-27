
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.Employee;
import Realdolmen.MyCareer.domain.Function;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Employee findEmployeeById(Long employeeId);
}
