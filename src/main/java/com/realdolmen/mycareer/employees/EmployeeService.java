
package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Optional<Employee> findById(Long employeeId) {
        return repository.findById(employeeId);
    }

    public void save(Employee emp) {
        repository.save(emp);
    }

}
