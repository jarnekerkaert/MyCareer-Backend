package Realdolmen.MyCareer.employees.controller;

import Realdolmen.MyCareer.employees.domain.Employee;
import Realdolmen.MyCareer.common.ResourceNotFoundException;
import Realdolmen.MyCareer.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Realdolmen.MyCareer.functions.service.FunctionService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    //Autowiring
    @Autowired
    EmployeeService employeeService;

    @Autowired
    FunctionService functionService;

// ------------------------------------------------------------------------------------------------------------------------------------------------
    // EMPLOYEE - GET
    /**
     * returns the information of the employee
     *
     * @param employeeId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") Long employeeId) {
        Optional<Employee> employee = employeeService.findById(employeeId);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
    }

// ------------------------------------------------------------------------------------------------------------------------------------------------
    // EMPLOYEE - POST - EXTRA
    /**
     * Extra POST API call for adding an employee
     *
     * @param employee
     * @return
     */
    /*
    @RequestMapping(method = RequestMethod.POST)
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }*/
}
