
package Realdolmen.MyCareer.employees.repositories;

import Realdolmen.MyCareer.employees.domain.Employee;
import Realdolmen.MyCareer.functions.domain.Function;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Employee findEmployeeById(Long employeeId);


}
