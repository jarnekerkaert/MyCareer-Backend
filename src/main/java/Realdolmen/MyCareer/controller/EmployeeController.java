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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Realdolmen.MyCareer.service.IEmployeeService;
import Realdolmen.MyCareer.service.IFunctionService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import Realdolmen.MyCareer.service.IQualityService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    
    //Autowiring
    @Autowired
    IEmployeeService employeeService;
    
    @Autowired
    IFunctionService functionService;
    
    @Autowired
    IQualityService qualityService;
    
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
     * the employee_id should be in each of the functions
     * @param json the given body should be an object that consists of two lists with the names currentfunctions and prevfunctions
     * currentfunctions is a list of current functions, prevfunctions is a list of prev functions
     * @return 
     */
    @RequestMapping(value = "/functions", method = RequestMethod.POST)
    public String postCurrentAndPreviousFunctions(@RequestBody FunctionListWrapper json){
        return functionService.saveTwoListsOfFunctions(json.getCurrentfunctions(), json.getPrevfunctions());
    } 
    
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    
    // QUALITY - POST
    
    /**
     * POST API call 
     * for adding one weak quality
     * @param quality the foreign key employee_id should be in the quality
     * @return 
     */
    @RequestMapping(value = "/weakquality", method = RequestMethod.POST)
    public Quality postWeakQuality(@Valid @RequestBody WeakQuality quality){
        return qualityService.save(quality);
    } 
    
    /**
     * POST API call 
     * for adding one strong quality
     * @param quality the foreign key employee_id should be in the quality
     * @return 
     */
    
    @RequestMapping(value = "/strongquality", method = RequestMethod.POST)
    public Quality postStrongQuality(@Valid @RequestBody StrongQuality quality){
        return qualityService.save(quality);
    } 
    
     /**
     * POST API call 
     * for adding a list of strong qualities
     * @param qualities the employee_id should be in each of the qualities
     * @return 
     */
    @RequestMapping(value = "/strongqualities", method = RequestMethod.POST)
    public List<Quality> postStrongQualities(@Valid @RequestBody List<StrongQuality> qualities){
        return qualityService.saveListOfStrongQualities(qualities);
    } 

    /**
     * POST API call 
     * for adding a list of weak qualities
     * @param qualities the employee_id should be in each of the qualities
     * @return 
     */
    @RequestMapping(value = "/weakqualities", method = RequestMethod.POST)
    public List<Quality> postWeakQualities(@Valid @RequestBody List<WeakQuality> qualities){
        return qualityService.saveListOfWeakQualities(qualities);
    } 
    
     /**
     * POST API call for adding a list of strong qualities and a list of weak qualities
     * the employee_id should be in each of the functions
     * @param json the given body should be an object that consists of two lists with the names strongqualities and weakqualities
     * strongqualities is a list of strong qualities, weakqualities is a list of weak qualities
     * @return 
     */
    @RequestMapping(value = "/qualities", method = RequestMethod.POST)
    public String postStrongAndWeakQualities(@RequestBody QualityListWrapper json){
        return qualityService.saveTwoListsOfQualities(json.getStrongqualities(), json.getWeakqualities());
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
    public List<StrongQuality> getCurrentFunctions(){
        return functionService.findAllCurrentFunctions();
    }
    
    /**
     * Extra
     * GET API call
     * returns all the previous functions that are stored in the database
     * @return 
     */
    @RequestMapping(value = "/allpreviousfunctions", method = RequestMethod.GET)
    public List<StrongQuality> getPreviousFunctions(){
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
