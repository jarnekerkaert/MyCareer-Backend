package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.domain.Employee;
import Realdolmen.MyCareer.domain.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Realdolmen.MyCareer.service.IEmployeeService;
import Realdolmen.MyCareer.service.IFunctionService;
import java.util.List;

@RestController
public class EmployeeController {
    
    @Autowired
    IEmployeeService employeeService;
    
    @Autowired
    IFunctionService functionService;
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "test";
    }
    
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") Long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }
    
    @RequestMapping(value = "/employee/{id}/functions", method = RequestMethod.GET)
    public List<Function> getFunctionsOfEmployee(@PathVariable("id") Long employeeId){
        return functionService.findByEmployee_id(employeeId);
    }
}
