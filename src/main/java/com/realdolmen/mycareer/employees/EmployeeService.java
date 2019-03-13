
package com.realdolmen.mycareer.employees;

import java.util.Optional;

public interface EmployeeService {
    Employee findEmployeeById(Long employeeId);

    Optional<Employee> findById(Long employeeId);

    Employee save(Employee emp);
}
