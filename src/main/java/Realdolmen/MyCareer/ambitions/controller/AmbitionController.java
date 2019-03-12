package Realdolmen.MyCareer.ambitions.controller;

import Realdolmen.MyCareer.ambitions.domain.Ambition;
import Realdolmen.MyCareer.ambitions.service.AmbitionService;
import Realdolmen.MyCareer.common.ResourceNotFoundException;
import Realdolmen.MyCareer.employees.domain.Employee;
import Realdolmen.MyCareer.employees.service.EmployeeService;
import Realdolmen.MyCareer.functions.domain.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AmbitionController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AmbitionService ambitionService;

    @RequestMapping(value = "/ambitions", method = RequestMethod.GET)
    public List<Ambition> getAmbitions(){
        return ambitionService.findAll();
    }

    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.GET)
    public List<Ambition> getAmbitionsEmployee(@PathVariable("id") Long employeeId){
        return ambitionService.findByEmployeeId(employeeId);
    }

    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.POST)
    public void postAmbitions(@PathVariable("id") Long employeeId,@RequestBody List<Ambition> ambitions){
        Optional<Employee> emp = employeeService.findById(employeeId);

        if(emp.isPresent()){
            emp.get().addAmbitions(ambitions);
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }

        ambitionService.deleteByEmployeeId(employeeId);
        ambitionService.saveAmbitions(ambitions);
    }

    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.PUT)
    public Employee updateAmbitions(@PathVariable("id") Long employeeId, List<Ambition> ambitions) {
        Optional<Employee> emp = employeeService.findById(employeeId);

        if(emp.isPresent()){
            emp.get().setAmbitions(ambitions);
            employeeService.save(emp.get());
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
        return emp.get();
    }

    @RequestMapping(value = "employees/{id}/ambitions", method = RequestMethod.DELETE)
    public void deleteAmbitions(@PathVariable("id") Long employeeId) {
        ambitionService.deleteByEmployeeId(employeeId);
    }
}
