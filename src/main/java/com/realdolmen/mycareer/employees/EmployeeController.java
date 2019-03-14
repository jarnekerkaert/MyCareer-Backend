package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.Employee;
import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.functions.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/employees")
public
class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService, FunctionService functionService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Employee getEmployee(@PathVariable("id") Long employeeId) {
        Employee emp = employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return emp;
    }

    @RequestMapping(method = RequestMethod.POST)
    void createEmployee(@Valid @RequestBody Employee employee) {
        employeeService.save(employee);
    }
}
