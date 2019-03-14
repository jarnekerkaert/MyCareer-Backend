
package com.realdolmen.mycareer.functions;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Function;
import com.realdolmen.mycareer.employees.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FunctionController {

    private final
    EmployeeService employeeService;

    private final
    FunctionService functionService;

    @Autowired
    public FunctionController(EmployeeService employeeService, FunctionService functionService) {
        this.employeeService = employeeService;
        this.functionService = functionService;
    }

    @RequestMapping(value = "/functions", method = RequestMethod.GET)
    public List<Function> getFunctions() {
        return functionService.findAll();
    }

    @RequestMapping(value = "/currentfunctions", method = RequestMethod.GET)
    public List<Function> getCurrentFunctions() {
        return functionService.findAllCurrentFunctions();
    }

    @RequestMapping(value = "/prevfunctions", method = RequestMethod.GET)
    public List<Function> getPreviousFunctions() {
        return functionService.findAllPrevFunctions();
    }

    @RequestMapping(value = "/employees/{id}/currentfunctions", method = RequestMethod.GET)
    public List<Function> getCurrentFunctionsOfEmployee(@PathVariable("id") Long employeeId) {
        return functionService.findCurrentFunctions(employeeId);
    }

    @RequestMapping(value = "/employees/{id}/prevfunctions", method = RequestMethod.GET)
    public List<Function> getPreviousFunctionsOfEmployee(@PathVariable("id") Long employeeId) {
        Employee emp = employeeService
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return functionService.findPrevFunctions(employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/functions", method = RequestMethod.DELETE)
    public void deleteAllByEmployee(@PathVariable("id") Long id) {
        functionService.deleteByEmployeeId(id);
    }


    @RequestMapping(value = "/employees/{id}/functions", method = RequestMethod.GET)
    public List<Function> getFunctionsOfEmployee(@PathVariable("id") Long employeeId) {
        return functionService.findByEmployeeId(employeeId);
    }

    /*
    @RequestMapping(value = "/employees/{id}/function", method = RequestMethod.POST)
    public Function postFunction(@PathVariable("id") Long employeeId, @Valid @RequestBody Function function){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null){
            emp.addFunction(function);
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        return functionService.save(function);
    } */

    @Transactional
    @RequestMapping(value = "/employees/{id}/functions", method = RequestMethod.POST)
    public void postFunctions(@PathVariable("id") Long employeeId, @RequestBody List<Function> functions) {
        functionService.deleteByEmployeeId(employeeId);
        functionService.saveFunctions(functions);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/functions", method = RequestMethod.PUT)
    public void updateFunctions(@PathVariable("id") Long employeeId, @RequestBody List<Function> functions) {
        functionService.deleteByEmployeeId(employeeId);
        functionService.saveFunctions(functions);
    }

    @RequestMapping(value = "/functions/{id}", method = RequestMethod.DELETE)
    public void deleteFunction(@PathVariable("id") Long id) {
        Optional<Function> function = functionService.findById(id);
        if (function.isPresent()) {
            functionService.deleteFunction(function.get());
        } else {
            throw new ResourceNotFoundException("Functie", "id", id);
        }
    }
}
