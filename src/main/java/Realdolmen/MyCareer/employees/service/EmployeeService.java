
package Realdolmen.MyCareer.employees.service;

import Realdolmen.MyCareer.functions.domain.Function;
import Realdolmen.MyCareer.employees.domain.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee findEmployeeById(Long employeeId);

    Optional<Employee> findById(Long employeeId);

    Employee save(Employee emp);
}
