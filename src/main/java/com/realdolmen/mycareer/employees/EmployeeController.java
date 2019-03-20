package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") Long employeeId) {
        return employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createEmployee(@Valid @RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("id") Long employeeId, @Valid @RequestBody Employee employee) {
        employeeService.findById(employeeId)
                .map(e -> {
                    employeeService.save(employee);
                    return e;
                }).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }
}
