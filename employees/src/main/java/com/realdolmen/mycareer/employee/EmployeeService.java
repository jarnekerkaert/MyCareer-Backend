
package com.realdolmen.mycareer.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
 class EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    Optional<Employee> findById(Long employeeId) {
        return repository.findById(employeeId);
    }

    void save(Employee emp) {
        repository.save(emp);
    }

}
