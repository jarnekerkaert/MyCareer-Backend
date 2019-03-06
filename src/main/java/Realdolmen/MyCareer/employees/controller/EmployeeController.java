package Realdolmen.MyCareer.employees.controller;

import Realdolmen.MyCareer.employees.domain.Employee;
import Realdolmen.MyCareer.functions.domain.CurrentFunction;
import Realdolmen.MyCareer.functions.domain.Function;
import Realdolmen.MyCareer.functions.domain.FunctionListWrapper;
import Realdolmen.MyCareer.functions.domain.PrevFunction;
import Realdolmen.MyCareer.qualities.domain.StrongQuality;
import Realdolmen.MyCareer.qualities.domain.Quality;
import Realdolmen.MyCareer.qualities.domain.QualityListWrapper;
import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import Realdolmen.MyCareer.common.ResourceNotFoundException;
import Realdolmen.MyCareer.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import Realdolmen.MyCareer.functions.service.FunctionService;
import Realdolmen.MyCareer.qualities.service.QualityService;

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
     * @param employeeId
     * @return 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public Employee getEmployee(@PathVariable("id") Long employeeId) {
            Employee employee = employeeService.findEmployeeById(employeeId);
            if(employee != null){
                return employee;
            }
            else{
                throw new ResourceNotFoundException("Employee", "id", employeeId);
            }
    }
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    // EMPLOYEE - POST - EXTRA
    /**
     * Extra 
     * POST API call 
     * for adding an employee
     * @param employee
     * @return 
     */
     /*
    @RequestMapping(method = RequestMethod.POST)
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }*/

}
