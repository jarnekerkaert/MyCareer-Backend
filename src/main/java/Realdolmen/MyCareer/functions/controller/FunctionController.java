
package Realdolmen.MyCareer.functions.controller;

import Realdolmen.MyCareer.functions.domain.Function;
import Realdolmen.MyCareer.employees.domain.Employee;
import Realdolmen.MyCareer.common.ResourceNotFoundException;
import Realdolmen.MyCareer.employees.service.EmployeeService;
import Realdolmen.MyCareer.functions.service.FunctionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * @param employeeId
     * @return 
     */
    @RequestMapping(value = "/employees/{id}/currentfunctions", method = RequestMethod.GET)
    public List<Function> getCurrentFunctionsOfEmployee(@PathVariable("id") Long employeeId){
        Employee employee = employeeService.findEmployeeById(employeeId);
        if(employee == null){
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
        return functionService.findCurrentFunctions(employeeId);
    }
    
    /**
     * GET API call
     * returns all the previous functions of the given employee
     * @param employeeId
     * @return 
     */
    @RequestMapping(value = "/employees/{id}/prevfunctions", method = RequestMethod.GET)
    public List<Function> getPreviousFunctionsOfEmployee(@PathVariable("id") Long employeeId){
        Employee employee = employeeService.findEmployeeById(employeeId);
        if(employee == null){
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
        return functionService.findPrevFunctions(employeeId);
    }
    
      // Extra: returns all the functions of a given employee
//    @RequestMapping(value = "/{id}/functions", method = RequestMethod.GET)
//    public List<Function> getFunctionsOfEmployee(@PathVariable("id") Long employeeId){
//        return functionService.findByEmployeeId(employeeId);
//    }
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    // FUNCTION - POST
    
    /**
     * Extra
     * POST API call 
     * for adding one current function
     * @param function the foreign key employeeId should be in the function
     * @return 
     */
    /*
    @RequestMapping(value = "/postcurrentfunction/{id}", method = RequestMethod.POST)
    public Function postCurrentFunction(@PathVariable("id") Long employeeId, @Valid @RequestBody CurrentFunction function){
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
     * Extra
     * POST API call 
     * for adding one previous function
     * @param function the foreign key employeeId should be in the function
     * @return 
     */
    /*
    @RequestMapping(value = "/postpreviousfunction/{id}", method = RequestMethod.POST)
    public Function postPreviousFunction(@PathVariable("id") Long employeeId, @Valid @RequestBody PrevFunction function){
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
     * Extra
     * POST API call 
     * for adding a list of current functions
     * @param functions the employeeId should be in each of the functions
     * @return 
     */
    /*
    @RequestMapping(value = "/postcurrentfunctions/{id}", method = RequestMethod.POST)
    public List<Function> postCurrentFunctions(@PathVariable("id") Long employeeId,@Valid @RequestBody List<CurrentFunction> functions){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null){
            emp.addCurrentFunctions(functions);
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        return functionService.saveListOfCurrentFunctions(functions);
    } */

    /**
     * Extra
     * POST API call 
     * for adding a list of previous functions
     * @param functions the employeeId should be in each of the functions
     * @return 
     */
    /*
    @RequestMapping(value = "/postpreviousfunctions/{id}", method = RequestMethod.POST)
    public List<Function> postPreviousFunctions(@PathVariable("id") Long employeeId,@Valid @RequestBody List<PrevFunction> functions){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null){
            emp.addPrevFunctions(functions);
        }
        else{
            throw new ResourceNotFoundException("Quality", "id", employeeId);
         }
        return functionService.saveListOfPrevFunctions(functions);
    } */
    
    /**
     * POST API call for adding a list of current functions and a list of previous functions
     * the employeeId should be in each of the functions
     * @param json the given body should be an object that consists of two lists with the names currentfunctions and prevfunctions
     * currentfunctions is a list of current functions, prevfunctions is a list of prev functions
     * @return 
     */
     /*
    @RequestMapping(value = "/functions/{id}", method = RequestMethod.POST)
    public void postCurrentAndPreviousFunctions(@PathVariable("id") Long employeeId,@RequestBody FunctionListWrapper json){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null){
            emp.addCurrentFunctions(json.getCurrentfunctions());
            emp.addPrevFunctions(json.getPrevfunctions());
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        functionService.saveTwoListsOfFunctions(json.getCurrentfunctions(), json.getPrevfunctions());
    } */
    
    /**
     * Adds a list of functions in the database to the given employee
     * @param employeeId the id of the employee
     * @param functions the functions that will be stored in the database and added to the employee
     */
    @RequestMapping(value = "/employees/{id}/functions", method = RequestMethod.POST)
    public void postFunctions(@PathVariable("id") Long employeeId,@RequestBody List<Function> functions){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null){
            emp.addFunctions(functions);
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        functionService.saveFunctions(functions);
    } 
}
