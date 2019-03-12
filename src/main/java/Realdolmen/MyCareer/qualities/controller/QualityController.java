
package Realdolmen.MyCareer.qualities.controller;

import Realdolmen.MyCareer.qualities.domain.Quality;
import Realdolmen.MyCareer.employees.domain.Employee;
import Realdolmen.MyCareer.common.ResourceNotFoundException;
import Realdolmen.MyCareer.employees.service.EmployeeService;
import Realdolmen.MyCareer.qualities.domain.QualityType;
import Realdolmen.MyCareer.qualities.service.QualityService;
//import Realdolmen.MyCareer.qualities.domain.StrongQuality;
//import Realdolmen.MyCareer.qualities.domain.WeakQuality;
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
        return qualityService.findAllStrongQualities(employeeId);
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
        return qualityService.findAllWeakQualities(employeeId);
    }

// ------------------------------------------------------------------------------------------------------------------------------------------------

    // QUALITY - POST

     /**
     * POST API call for adding a list of qualities
     * @param qualities the list of qualities
     * @return
     */
    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.POST)
    public void postQualities(@PathVariable("id") Long employeeId,@RequestBody List<Quality> qualities){
        Employee emp= employeeService.findEmployeeById(employeeId);
         if(emp != null ){
             emp.addQualities(qualities);

         }
         else{
             throw new ResourceNotFoundException("Employee", "id", employeeId);
         }
        qualityService.deleteByEmployeeId(employeeId);
        qualityService.saveQualities(qualities);
    }

// ------------------------------------------------------------------------------------------------------------------------------------------------

    // QUALITY - DELETE

    @RequestMapping(value="/qualities/{id}", method = RequestMethod.DELETE)
    public void deleteQuality(@PathVariable("id") Long id){
            Quality quality = qualityService.findQualityById(id);
            if (quality != null) {
                qualityService.deleteQuality(quality);
            }
            else
            {
                throw new ResourceNotFoundException("Sterk punt", "id", id);
            }
    }

}
