package com.realdolmen.mycareer.ambitions;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Ambition;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Function;
import com.realdolmen.mycareer.employees.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
public class AmbitionController {

    private final AmbitionService ambitionService;

    @Autowired
    public AmbitionController(AmbitionService ambitionService) {
        this.ambitionService = ambitionService;
    }

    @RequestMapping(value = "/ambitions", method = RequestMethod.GET)
    public List<Ambition> getAmbitions() {
        return ambitionService.findAll();
    }

    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.GET)
    public List<Ambition> getAmbitionsEmployee(@PathVariable("id") Long employeeId) {
        return ambitionService.findByEmployeeId(employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.POST)
    public void postAmbitions(@PathVariable("id") Long employeeId, @RequestBody List<Ambition> ambitions) {
//        employeeService.findById(employeeId)
//                .map(emp -> {
//                    emp.setAmbitions(ambitions);
////                    employeeService.save(emp);
//                    return emp;
//                })
//                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
//        Employee emp = employeeService.findById(employeeId);
//        emp.setAmbitions(ambitions);
        ambitionService.saveAmbitions(ambitions);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.PUT)
    public void updateAmbitions(@PathVariable("id") Long employeeId, @Valid @RequestBody List<Ambition> ambitions) {
//        List<Ambition> amb = ambitionService.findByEmployeeId(employeeId);
//        amb = ambitions;
        ambitionService.deleteByEmployeeId(employeeId);
        ambitionService.saveAmbitions(ambitions);
    }

    @Transactional
    @RequestMapping(value = "employees/{id}/ambitions", method = RequestMethod.DELETE)
    public void deleteAmbitions(@PathVariable("id") Long employeeId) {
        ambitionService.deleteByEmployeeId(employeeId);
    }
}
