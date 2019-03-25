package com.realdolmen.mycareer.Employees;

//import com.realdolmen.mycareer.common.ResourceNotFoundException;
//import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.CommonLibrary.common.ResourceNotFoundException;
import com.realdolmen.mycareer.CommonLibrary.common.dto.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeModel getEmployee(@PathVariable("id") Long employeeId) {
        Employee emp = employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return convertToDTO(emp);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createEmployee(@Valid @RequestBody EmployeeModel employee) {
            employeeService.save(convertToEmployee(employee));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("id") Long employeeId, @Valid @RequestBody EmployeeModel employee) {
        Employee emp = employeeService.findById(employeeId)
                .map(e -> {
                    e = convertToEmployee(employee);
                    return e;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        employeeService.save(emp);
    }

    private Employee convertToEmployee(EmployeeModel model) {
        Employee emp = new Employee();
        emp.setId(model.getId());
        emp.setFirstname(model.getFirstname());
        emp.setLastname(model.getLastname());
        emp.setEmail(model.getEmail());
        emp.setBirthdate(model.getBirthdate());
        return emp;
    }

    private EmployeeModel convertToDTO(Employee emp) {
        EmployeeModel model = new EmployeeModel();
        model.setId(emp.getId());
        model.setFirstname(emp.getFirstname());
        model.setLastname(emp.getLastname());
        model.setBirthdate(emp.getBirthdate());
        model.setEmail(emp.getEmail());
        return model;
    }
}
