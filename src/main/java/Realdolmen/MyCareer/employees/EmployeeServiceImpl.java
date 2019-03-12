
package Realdolmen.MyCareer.employees;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee findEmployeeById(Long employeeId) {
        return repository.findEmployeeById(employeeId);
    }

    @Override
    public Optional<Employee> findById(Long employeeId) {
        return repository.findById(employeeId);
    }

    @Override
    public Employee save(Employee emp) {
        return repository.save(emp);
    }

    
}
