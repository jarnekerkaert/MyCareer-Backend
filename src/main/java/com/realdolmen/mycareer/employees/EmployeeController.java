package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.functions.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        Employee emp = employeeService.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return emp;
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
