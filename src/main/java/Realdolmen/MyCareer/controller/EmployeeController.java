package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.domain.CurrentFunction;
import Realdolmen.MyCareer.domain.Employee;
import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.PrevFunction;
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
    
    //Autowiring
    @Autowired
    IEmployeeService employeeService;
    
    @Autowired
    IFunctionService functionService;
    
    @Autowired
    ISubklasseService subklasseService;
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    
    // EMPLOYEE - GET
    /**
     * returns the information of the employee
     * @param employeeId
     * @return 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable("id") Long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }
// ------------------------------------------------------------------------------------------------------------------------------------------------
    // FUNCTION - GET
        
    /**
     * GET API call
     * returns all the current functions of the given employee
     * @param employee_id
     * @return 
     */
    @RequestMapping(value = "/allcurrentfunctionsofemployee/{id}", method = RequestMethod.GET)
    public List<Function> getCurrentFunctionsOfEmployee(@PathVariable("id") Long employee_id){
        return functionService.findAllCurrentFunctionsOfEmployee(employee_id);
    }
    
     /**
     * GET API call
     * returns all the previous functions of the given employee
     * @param employee_id
     * @return 
     */
    @RequestMapping(value = "/allpreviousfunctionsofemployee/{id}", method = RequestMethod.GET)
    public List<Function> getPreviousFunctionsOfEmployee(@PathVariable("id") Long employee_id){
        return functionService.findAllPreviousFunctionsOfEmployee(employee_id);
    }
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    // FUNCTION - POST
    
    /**
     * POST API call 
     * for adding one current function
     * @param function the foreign key employee_id should be in the function
     * @return 
     */
    @RequestMapping(value = "/postcurrentfunction", method = RequestMethod.POST)
    public Function postCurrentFunction(@Valid @RequestBody CurrentFunction function){
        /*
        Employee emp = employeeService.findEmployeeById(employeeId);
        // in de body zetten
        //function.setEmployee(emp);
        List<Function> oudeLijst = emp.getFunctions();
        System.out.println(oudeLijst);
        //List<Function> oudeLijst = functionService.findCurrentFunctions(employeeId);
        oudeLijst.add(function);
        emp.setFunctions(oudeLijst);
        employeeService.save(emp);
        return functionService.save(function);
        // enkel 1 functie toevoegen hier, dus enkel 1 functie in db toevoegen
        // hmmmm, worden de functie-objecten in databank dan wel aangemaakt?
        */
        return functionService.save(function);
    } 
    
    /**
     * POST API call 
     * for adding one previous function
     * @param function the foreign key employee_id should be in the function
     * @return 
     */
    @RequestMapping(value = "/postpreviousfunction", method = RequestMethod.POST)
    public Function postPreviousFunction(@Valid @RequestBody PrevFunction function){
        return functionService.save(function);
    } 
    
    /**
     * POST API call 
     * for adding a list of current functions
     * @param functions the employee_id should be in each of the functions
     * @return 
     */
    @RequestMapping(value = "/postcurrentfunctions", method = RequestMethod.POST)
    public List<Function> postCurrentFunctions(@Valid @RequestBody List<CurrentFunction> functions){
        return functionService.saveListOfCurrentFunctions(functions);
    } 

    /**
     * POST API call 
     * for adding a list of previous functions
     * @param functions the employee_id should be in each of the functions
     * @return 
     */
    @RequestMapping(value = "/postpreviousfunctions", method = RequestMethod.POST)
    public List<Function> postPreviousFunctions(@Valid @RequestBody List<PrevFunction> functions){
        return functionService.saveListOfPrevFunctions(functions);
    } 
    
    /**
     * POST API call for adding a list of current functions and a list of previous functions
     * @param currentfunctions the employee_id should be in each of the functions
     * @param prevfunctions the employee_id should be in each of the functions
     * @return 
     */
    
    /*
    @RequestMapping(value = "/postcurrentandprevfunctions", method = RequestMethod.POST)
    public String postCurrentAndPreviousFunctions(@Valid @RequestBody List<CurrentFunction> currentfunctions, @Valid @RequestBody List<PrevFunction> prevfunctions){
        return functionService.saveTwoListsOfFunctions(currentfunctions, prevfunctions);
        //postCurrentFunctions(currentfunctions);
        //postPreviousFunctions(prevfunctions);
        //return "success";
    } */
    
    /*
    @RequestMapping(value = "/postcurrentandprevfunctionstest", method = RequestMethod.POST)
    public String postCurrentAndPreviousFunctionsTest(@RequestBody List<List<Function>> json){
        return functionService.saveTwoListsOfFunctions(json.get(0), json.get(1));
    } */
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    // EMPLOYEE - POST - EXTRA
    /**
     * Extra 
     * POST API call 
     * for adding an employee
     * @param employee
     * @return 
     */
    @RequestMapping(value = "/postemployee", method = RequestMethod.POST)
    public Employee createUser(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    // EXTRA - kan misschien nog nuttig zijn voor in de toekomst
    
    /**
     * Extra
     * GET API call
     * returns all the current functions that are stored in the database
     * @return 
     */
    @RequestMapping(value = "/allcurrentfunctions", method = RequestMethod.GET)
    public List<Subklasse1> getCurrentFunctions(){
        return functionService.findAllCurrentFunctions();
    }
    
    /**
     * Extra
     * GET API call
     * returns all the previous functions that are stored in the database
     * @return 
     */
    @RequestMapping(value = "/allpreviousfunctions", method = RequestMethod.GET)
    public List<Subklasse1> getPreviousFunctions(){
        return functionService.findAllPreviousFunctions();
    }
    
//    @RequestMapping(value = "/{id}/functions", method = RequestMethod.GET)
//    public List<Function> getFunctionsOfEmployee(@PathVariable("id") Long employeeId){
//        return functionService.findByEmployee_id(employeeId);
//    }
    
//    @RequestMapping(value = "/{id}/subklasse", method = RequestMethod.GET)
//    public Superklasse getSubklasse1s(@PathVariable("id") Long subklasseId){
//        return subklasseService.findSubklasseById(subklasseId);
//    }
//    
//    @RequestMapping(value = "/allsubs1", method = RequestMethod.GET)
//    public List<Subklasse1> getSubklasse1s(){
//        return subklasseService.findAll1();
//    }
//    
//    @RequestMapping(value = "/allsubs2", method = RequestMethod.GET)
//    public List<Subklasse1> getSubklasse2s(){
//        return subklasseService.findAll2();
//    }
}
