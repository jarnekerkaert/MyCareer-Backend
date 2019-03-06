
package Realdolmen.MyCareer.employees.service;

import Realdolmen.MyCareer.functions.domain.Function;
import Realdolmen.MyCareer.employees.domain.Employee;
import java.util.List;

public interface EmployeeService {
    public Employee findEmployeeById(Long employeeId);
    public Employee save(Employee emp);
    
}
