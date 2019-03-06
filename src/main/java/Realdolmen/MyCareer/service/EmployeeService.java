
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.Employee;
import java.util.List;

public interface EmployeeService {
    public Employee findEmployeeById(Long employeeId);
    public Employee save(Employee emp);
    
}
