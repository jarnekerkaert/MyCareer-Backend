
package com.realdolmen.mycareer.qualities;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Ambition;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Quality;
import com.realdolmen.mycareer.employees.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QualityController {

    private final
    EmployeeService employeeService;

    private final
    QualityService qualityService;

    @Autowired
    public QualityController(EmployeeService employeeService, QualityService qualityService) {
        this.employeeService = employeeService;
        this.qualityService = qualityService;
    }

    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.GET)
    public List<Quality> getAllQualitiesEmployee(@PathVariable("id") Long employeeId) {
        return qualityService.findByEmployeeId(employeeId);
    }

    @RequestMapping(value = "/employees/{id}/strongqualities", method = RequestMethod.GET)
    public List<Quality> getStrongQualitiesOfEmployee(@PathVariable("id") Long employeeId) {
        Employee emp = employeeService
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return qualityService.findAllStrongQualities(employeeId);
    }

    @RequestMapping(value = "/employees/{id}/weakqualities", method = RequestMethod.GET)
    public List<Quality> getWeakQualitiesOfEmployee(@PathVariable("id") Long employeeId) {
        Employee emp = employeeService
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return qualityService.findAllWeakQualities(employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.POST)
    public void postQualities(@PathVariable("id") Long employeeId, @RequestBody List<Quality> qualities) {
        qualityService.saveQualities(qualities);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.PUT)
    public void updateQualities(@PathVariable("id") Long employeeId, @RequestBody List<Quality> qualities) {
//        List<Quality> qual = qualityService.findByEmployeeId(employeeId);
//        qual = qualities;
        qualityService.deleteByEmployeeId(employeeId);
        qualityService.saveQualities(qualities);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.DELETE)
    public void deleteAllByEmployeeId(@PathVariable("id") Long id) {
        qualityService.deleteByEmployeeId(id);
    }

    @RequestMapping(value = "/qualities/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") Long id) {
        qualityService.findQualityById(id)
                .map(q -> {
                    qualityService.deleteQuality(q);
                    return q;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Sterk punt", "id", id));
    }
}
