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
     * returns all the current functions of the given employee
     * @param employee_id
     * @return 
     */
    @RequestMapping(value = "/allcurrentfunctionsofemployee/{id}", method = RequestMethod.GET)
    public List<Function> getCurrentFunctionsOfEmployee(@PathVariable("id") Long employee_id){
        return functionService.findAllCurrentFunctionsOfEmployee(employee_id);
    }
    
     /**
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
    
    @RequestMapping(value = "/postpreviousfunction", method = RequestMethod.POST)
    public Function postPreviousFunction(@Valid @RequestBody PrevFunction function){
        return functionService.save(function);
    } 
    
    // post een lijst van huidige functies 
            // for elk element in de lijst die meegegeven wordt moet je een nieuwe functie in db maken
    @RequestMapping(value = "/postcurrentfunctions", method = RequestMethod.POST)
    public List<Function> postCurrentFunctions(@Valid @RequestBody List<CurrentFunction> functions){
        return functionService.saveListOfCurrentFunctions(functions);
    } 

    // post een lijst van vorige functies
    @RequestMapping(value = "/postpreviousfunctions", method = RequestMethod.POST)
    public List<Function> postPreviousFunctions(@Valid @RequestBody List<PrevFunction> functions){
        return functionService.saveListOfPrevFunctions(functions);
    } 
    
    // 1 api call om zowel een lijst van huidige als vorige functies op te slaan
    @RequestMapping(value = "/postcurrentandprevfunctions", method = RequestMethod.POST)
    public String postCurrentAndPreviousFunctions(@Valid @RequestBody List<CurrentFunction> currentfunctions, @Valid @RequestBody List<PrevFunction> prevfunctions){
        return functionService.saveTwoListsOfFunctions(currentfunctions, prevfunctions);
    } 
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    // EMPLOYEE - POST - EXTRA
    @RequestMapping(value = "/postemployee", method = RequestMethod.POST)
    public Employee createUser(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    // EXTRA - kan misschien nog nuttig zijn voor in de toekomst
    
    /**
     * returns all the current functions that are stored in the database
     * @return 
     */
    @RequestMapping(value = "/allcurrentfunctions", method = RequestMethod.GET)
    public List<Subklasse1> getCurrentFunctions(){
        return functionService.findAllCurrentFunctions();
    }
    
    /**
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
