package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.functions.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, FunctionService functionService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Employee getEmployee(@PathVariable("id") Long employeeId) {
        Employee emp = employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return emp;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    void createEmployee(@Valid @RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("id") Long employeeId,@RequestBody Employee employee) {
        Employee emp = employeeService.findById(employeeId)
                .map(e -> {
                    e.setId(employeeId);
                    e.setFirstname(employee.getFirstname());
                    e.setLastname(employee.getLastname());
                    e.setBirthdate(employee.getBirthdate());
                    e.setCv_filepath(employee.getCv_filepath());
                    e.setEmail(employee.getEmail());
                    e.setPassword(employee.getPassword());
                    return e;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        employeeService.save(emp);
    }
    
}
