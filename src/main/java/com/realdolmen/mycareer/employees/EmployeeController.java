package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.ValidationException;
import com.realdolmen.mycareer.common.dto.AmbitionModel;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.common.dto.QualityModel;
import com.realdolmen.mycareer.common.dto.RoleModel;
import com.realdolmen.mycareer.roles.RoleService;
import java.util.List;
import java.util.Optional;
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
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public 
        //EmployeeModel 
        Employee getEmployee(@PathVariable("id") Long employeeId) {
        Employee emp = employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        //return convertToDTO(emp);
        return emp;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createEmployee(@Valid @RequestBody Employee employee) {
            employeeService.save(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("id") Long employeeId, @Valid @RequestBody EmployeeModel employee) {
        Employee emp = employeeService.findById(employeeId)
                .map(e -> {
                    e = convertToEmployee(employeeId, employee);
                    return e;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        employeeService.save(emp);
    }
    
    private Employee convertToEmployee(Long id, EmployeeModel model) {
        Employee emp = new Employee();

        emp.setId(id);
        emp.setFirstname(model.getFirstname());
        emp.setLastname(model.getLastname());
        emp.setEmail(model.getEmail());
        emp.setBirthdate(model.getBirthdate());
        emp.setCv_filepath(model.getCv_filepath());

        return emp;
    }
    
    private EmployeeModel convertToDTO(Employee emp) {
        //List<Ambition> ambitions) {
        EmployeeModel model = new EmployeeModel(emp);
        return model;
    }
}
