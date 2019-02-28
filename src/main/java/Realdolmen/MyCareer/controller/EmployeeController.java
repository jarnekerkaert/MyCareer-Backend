package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.domain.Employee;
import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.Subklasse1;
import Realdolmen.MyCareer.domain.Superklasse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Realdolmen.MyCareer.service.IEmployeeService;
import Realdolmen.MyCareer.service.IFunctionService;
import Realdolmen.MyCareer.service.ISubklasseService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    
    @Autowired
    IEmployeeService employeeService;
    
    @Autowired
    IFunctionService functionService;
    
    @Autowired
    ISubklasseService subklasseService;
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "test";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") Long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }
    
    @RequestMapping(value = "/{id}/functions", method = RequestMethod.GET)
    public List<Function> getFunctionsOfEmployee(@PathVariable("id") Long employeeId){
        return functionService.findByEmployee_id(employeeId);
    }
    /*
    @RequestMapping(value = "/{id}/functions", method = RequestMethod.POST)
    public Function postFunction(@PathVariable("id") Long employeeId, @Valid @RequestBody Function function){
        Employee emp = employeeService.findEmployeeById(employeeId);
        function.setEmployee(emp);
        return functionService.save(function);
        
    } */
    
    @RequestMapping(value = "/{id}/subklasse", method = RequestMethod.GET)
    public Superklasse getSubklasse1s(@PathVariable("id") Long subklasseId){
        return subklasseService.findSubklasseById(subklasseId);
    }
    
    @RequestMapping(value = "/allsubs1", method = RequestMethod.GET)
    public List<Subklasse1> getSubklasse1s(){
        return subklasseService.findAll1();
    }
    
    @RequestMapping(value = "/allsubs2", method = RequestMethod.GET)
    public List<Subklasse1> getSubklasse2s(){
        return subklasseService.findAll2();
    }
}
