package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.domain.CurrentFunction;
import Realdolmen.MyCareer.domain.Employee;
import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.FunctionListWrapper;
import Realdolmen.MyCareer.domain.PrevFunction;
import Realdolmen.MyCareer.domain.StrongQuality;
import Realdolmen.MyCareer.domain.Quality;
import Realdolmen.MyCareer.domain.QualityListWrapper;
import Realdolmen.MyCareer.domain.WeakQuality;
import Realdolmen.MyCareer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import Realdolmen.MyCareer.service.EmployeeService;
import Realdolmen.MyCareer.service.FunctionService;
import Realdolmen.MyCareer.service.QualityService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    
    //Autowiring
    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    FunctionService functionService;
    
    @Autowired
    QualityService qualityService;
    
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
    @RequestMapping(method = RequestMethod.POST)
    public Employee createUser(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }

}
