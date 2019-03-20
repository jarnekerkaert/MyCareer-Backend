package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.ValidationException;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.roles.RoleService;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    EmployeeController(EmployeeService employeeService) {
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
    void updateEmployee(@PathVariable("id") Long employeeId, @Valid @RequestBody Employee employee) {
        Employee emp = employeeService.findById(employeeId)
                .map(e -> {
                    e = employee;
                    return e;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        employeeService.save(emp);
    }
    
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  void handleConstraintViolationException(ConstraintViolationException e) throws ValidationException {
    throw new ValidationException();
  }
}
