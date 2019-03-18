package com.realdolmen.mycareer.ambitions;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Ambition;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Role;
import com.realdolmen.mycareer.employees.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AmbitionController {

    private final EmployeeService employeeService;

    private final AmbitionService ambitionService;

    @Autowired
    public AmbitionController(EmployeeService employeeService, AmbitionService ambitionService) {
        this.employeeService = employeeService;
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
        ambitionService.deleteByEmployeeId(employeeId);
        saveAmbitions(ambitions, employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.PUT)
    public void updateAmbitions(@PathVariable("id") Long employeeId, @RequestBody List<Ambition> ambitions) {
        ambitionService.deleteByEmployeeId(employeeId);
        saveAmbitions(ambitions, employeeId);
    }

    @Transactional
    @RequestMapping(value = "employees/{id}/ambitions", method = RequestMethod.DELETE)
    public void deleteAmbitions(@PathVariable("id") Long employeeId) {
        ambitionService.deleteByEmployeeId(employeeId);
    }

    private void saveAmbitions(List<Ambition> ambitions, Long employeeId) {
        ambitionService.saveAmbitions(ambitions.stream()
                .map(a -> {
                    a.setEmployeeId(employeeId);
                    return a;
                }).collect(Collectors.toList()));
    }
}
