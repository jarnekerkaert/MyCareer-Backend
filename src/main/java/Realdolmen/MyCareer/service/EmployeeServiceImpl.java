
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Realdolmen.MyCareer.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;
    
    @Override
    public Employee findEmployeeById(Long employeeId) {
        return repository.findEmployeeById(employeeId);
    }

    @Override
    public Employee save(Employee emp) {
        return repository.save(emp);
    }
    
}
