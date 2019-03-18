package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.functions.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Employee getEmployee(@PathVariable("id") Long employeeId) {
        Employee emp = employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return emp;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    void createEmployee(@Valid @RequestBody Employee employee
            //, Errors errors
        ) {
//        if (errors.hasErrors()) {
//            throw new IllegalArgumentException("Input must be valid!");
            //System.out.println(errors.getAllErrors());
//        }
        employeeService.save(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee employee) {
        Employee emp = employeeService.findById(employeeId)
                .map(e -> {
                    e = employee;
                    return e;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        employeeService.save(emp);
    }

}
