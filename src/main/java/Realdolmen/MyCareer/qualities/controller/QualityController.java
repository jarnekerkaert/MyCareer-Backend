
package Realdolmen.MyCareer.qualities.controller;

import Realdolmen.MyCareer.qualities.domain.QualityListWrapper;
import Realdolmen.MyCareer.qualities.domain.Quality;
import Realdolmen.MyCareer.employees.domain.Employee;
import Realdolmen.MyCareer.common.ResourceNotFoundException;
import Realdolmen.MyCareer.employees.service.EmployeeService;
import Realdolmen.MyCareer.qualities.service.QualityService;
import Realdolmen.MyCareer.qualities.domain.StrongQuality;
import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QualityController {
    
    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    QualityService qualityService;
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
   
    // QUALITY - GET
    
     /**
     * GET API call
     * returns all the strong qualities of the given employee
     * @param employeeId
     * @return 
     */
    @RequestMapping(value = "/employees/{id}/strongqualities", method = RequestMethod.GET)
    public List<Quality> getStrongQualitiesOfEmployee(@PathVariable("id") Long employeeId){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp == null){
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        return qualityService.findAllStrongQualitiesOfEmployee(employeeId);
    }
    
     /**
     * GET API call
     * returns all the weak qualities of the given employee
     * @param employeeId
     * @return 
     */
    @RequestMapping(value = "/employees/{id}/weakqualities", method = RequestMethod.GET)
    public List<Quality> getWeakQualitiesOfEmployee(@PathVariable("id") Long employeeId){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp == null){
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        return qualityService.findAllWeakQualitiesOfEmployee(employeeId);
    }
    
// ------------------------------------------------------------------------------------------------------------------------------------------------
    
    // QUALITY - POST
    
    /**
     * POST API call 
     * for adding one weak quality
     * @param quality the foreign key employeeId should be in the quality
     * @return 
     */
    /*
    @RequestMapping(value = "/weakquality/{id}", method = RequestMethod.POST)
    public Quality postWeakQuality(@PathVariable("id") Long employeeId,@Valid @RequestBody WeakQuality quality){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null){
            emp.addWeakQuality(quality);
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        return qualityService.save(quality);
    } */
    
    /**
     * POST API call 
     * for adding one strong quality
     * @param quality the foreign key employeeId should be in the quality
     * @return 
     */
    
    /*
    @RequestMapping(value = "/strongquality/{id}", method = RequestMethod.POST)
    public Quality postStrongQuality(@PathVariable("id") Long employeeId,@Valid @RequestBody StrongQuality quality){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null){
            emp.addStrongQuality(quality);
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        return qualityService.save(quality);
    } */
    
     /**
     * POST API call 
     * for adding a list of strong qualities
     * @param qualities the employeeId should be in each of the qualities
     * @return 
     */
    /*
    @RequestMapping(value = "/strongqualities/{id}", method = RequestMethod.POST)
    public List<Quality> postStrongQualities(@PathVariable("id") Long employeeId,@Valid @RequestBody List<StrongQuality> qualities){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null){
            emp.addStrongQualities(qualities);
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        return qualityService.saveListOfStrongQualities(qualities);
    } */

    /**
     * POST API call 
     * for adding a list of weak qualities
     * @param qualities the employeeId should be in each of the qualities
     * @return 
     */
    /*
    @RequestMapping(value = "/weakqualities/{id}", method = RequestMethod.POST)
    public List<Quality> postWeakQualities(@PathVariable("id") Long employeeId,@Valid @RequestBody List<WeakQuality> qualities){
        Employee emp = employeeService.findEmployeeById(employeeId);
        if(emp != null ){
            emp.addWeakQualities(qualities);
        }
        else{
             throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        return qualityService.saveListOfWeakQualities(qualities);
    } */
    
     /**
     * POST API call for adding a list of strong qualities and a list of weak qualities
     * the employeeId should be in each of the functions
     * @param json the given body should be an object that consists of two lists with the names strongqualities and weakqualities
     * strongqualities is a list of strong qualities, weakqualities is a list of weak qualities
     * @return 
     */
    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.POST)
    public void postStrongAndWeakQualities(@PathVariable("id") Long employeeId,@RequestBody QualityListWrapper json){
        Employee emp= employeeService.findEmployeeById(employeeId);
         if(emp != null ){
             emp.addStrongQualities(json.getStrongqualities());
             emp.addWeakQualities(json.getWeakqualities());
         }
         else{
             throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        qualityService.saveTwoListsOfQualities(json.getStrongqualities(), json.getWeakqualities());
    } 
// ------------------------------------------------------------------------------------------------------------------------------------------------

    // QUALITY - DELETE
    @RequestMapping(value="/strongquality/{id}", method = RequestMethod.DELETE)
    public void deleteStrongQuality(@PathVariable("id") Long id){
            StrongQuality quality = (StrongQuality) qualityService.findStrongQualityById(id);
            if (quality != null) {
                qualityService.deleteStrongQuality(quality);
            } 
            else 
            {
                throw new ResourceNotFoundException("Sterk punt", "id", id);
            }
    }
    
    @RequestMapping(value="/weakquality/{id}", method = RequestMethod.DELETE)
    public void deleteWeakQuality(@PathVariable("id") Long id){
            WeakQuality quality = (WeakQuality) qualityService.findWeakQualityById(id);
            if (quality != null) {
                qualityService.deleteWeakQuality(quality);
            } 
            else 
            {
                throw new ResourceNotFoundException("Groeipunt", "id", id);
            }
    }
    /*
    @RequestMapping(value="/quality/{id}", method = RequestMethod.DELETE)
    public String deleteQualityV2punt0(@PathVariable("id") Long id){
            StrongQuality quality = (StrongQuality) qualityService.findStrongQualityById(id).orElse(null);
            WeakQuality wquality = (WeakQuality) qualityService.findWeakQualityById(id).orElse(null);
            if (quality != null) {
                qualityService.deleteStrongQuality(quality);
            } 
            else if(wquality != null){
                qualityService.deleteWeakQuality(wquality);
            }
            else 
            {
                throw new ResourceNotFoundException("Punt", "id", id);
            }
    }*/
}
