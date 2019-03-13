
package com.realdolmen.mycareer.functions;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.employees.Employee;
import com.realdolmen.mycareer.employees.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FunctionController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    FunctionService functionService;

// ------------------------------------------------------------------------------------------------------------------------------------------------
    // FUNCTION - GET

    /**
     * Extra
     * GET API call
     * Returns all the functions that are stored in the database
     * @return
     */

    @RequestMapping(value = "/functions",method = RequestMethod.GET)
    public List<Function> getFunctions(){
        return functionService.findAll();
    }

    /**
     * Extra
     * GET API call
     * returns all the current functions that are stored in the database
     * @return
     */

    @RequestMapping(value = "/currentfunctions", method = RequestMethod.GET)
    public List<Function> getCurrentFunctions(){
        return functionService.findAllCurrentFunctions();
    }

    /**
     * Extra
     * GET API call
     * returns all the previous functions that are stored in the database
     * @return
     */

    @RequestMapping(value = "/prevfunctions", method = RequestMethod.GET)
    public List<Function> getPreviousFunctions(){
        return functionService.findAllPrevFunctions();
    }

    /**
     * GET API call
     * returns all the current functions of the given employee
     *
     * @param employeeId
     * @return
     */
    @RequestMapping(value = "/employees/{id}/currentfunctions", method = RequestMethod.GET)
    public List<Function> getCurrentFunctionsOfEmployee(@PathVariable("id") Long employeeId) {
        return functionService.findCurrentFunctions(employeeId);
    }

    /**
     * GET API call
     * returns all the previous functions of the given employee
     *
     * @param employeeId
     * @return
     */
    @RequestMapping(value = "/employees/{id}/prevfunctions", method = RequestMethod.GET)
    public List<Function> getPreviousFunctionsOfEmployee(@PathVariable("id") Long employeeId) {
        Employee emp = employeeService
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return functionService.findPrevFunctions(employeeId);
    }

    // DELETE ALL FUNCTIONS BY EMPLOYEEID
    @RequestMapping(value = "/employees/{id}/functions", method = RequestMethod.DELETE)
    public void deleteAllByEmployee(@PathVariable("id") Long id) {
        functionService.deleteByEmployeeId(id);
    }

    /**
     * Extra
     * GET API call
     * returns all the functions of a given employee - both current and previous functions
     * @param employeeId
     * @return
     */
//    @RequestMapping(value = "/{id}/functions", method = RequestMethod.GET)
//    public List<Function> getFunctionsOfEmployee(@PathVariable("id") Long employeeId){
//        return functionService.findByEmployeeId(employeeId);
//    }

// ------------------------------------------------------------------------------------------------------------------------------------------------
    // FUNCTION - POST

    /**
     * Extra
     * POST API call 
     * for adding one function
     * @param function
     * @return
     */
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

    /**
     * Adds a list of functions in the database to the given employee
     *
     * @param employeeId the id of the employee
     * @param functions  the functions that will be stored in the database and added to the employee
     */
    @RequestMapping(value = "/employees/{id}/functions", method = RequestMethod.POST)
    public void postFunctions(@PathVariable("id") Long employeeId, @RequestBody List<Function> functions) {

        employeeService
                .findById(employeeId)
                .map(emp -> {
                    emp.addFunctions(functions);
                    return emp;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        functionService.deleteByEmployeeId(employeeId);
        functionService.saveFunctions(functions);
    }

// ------------------------------------------------------------------------------------------------------------------------------------------------

    // FUNCTION - DELETE

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
