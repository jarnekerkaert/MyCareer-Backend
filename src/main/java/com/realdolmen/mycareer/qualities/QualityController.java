
package com.realdolmen.mycareer.qualities;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.ValidationException;
import com.realdolmen.mycareer.common.dto.QualityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class QualityController {

    private final
    QualityService qualityService;

    @Autowired
    public QualityController(QualityService qualityService) {
        this.qualityService = qualityService;
    }

    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.GET)
    public List<QualityModel> getAllQualitiesEmployee(@PathVariable("id") Long employeeId) {
        return qualityService.findByEmployeeId(employeeId).stream().map(q -> convertToDTO(q)).collect(Collectors.toList());
    }

//    @RequestMapping(value = "/employees/{id}/strongqualities", method = RequestMethod.GET)
//    public List<Quality> getStrongQualitiesOfEmployee(@PathVariable("id") Long employeeId) {
//        Employee emp = employeeService
//                .findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
//        return qualityService.findAllStrongQualities(employeeId);
//    }
//
//    @RequestMapping(value = "/employees/{id}/weakqualities", method = RequestMethod.GET)
//    public List<Quality> getWeakQualitiesOfEmployee(@PathVariable("id") Long employeeId) {
//        Employee emp = employeeService
//                .findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
//        return qualityService.findAllWeakQualities(employeeId);
//    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.POST)
    public void postQualities(@PathVariable("id") Long employeeId, @RequestBody List<Quality> qualities) {
        saveQualities(qualities,employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.PUT)
    public void updateQualities(@PathVariable("id") Long employeeId, @Valid @RequestBody List<QualityModel> qualities) //throws ValidationException
    {
//        try{
        qualityService.deleteByEmployeeId(employeeId);
        //qualityService.saveQualities(qualities);
        saveQualities(qualities.stream().map(q -> convertToQuality(q)).collect(Collectors.toList()), employeeId);
//        }catch(ConstraintViolationException|HttpServerErrorException e){
//            throw new ValidationException();
//        }
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

    private void saveQualities(List<Quality> qualities, Long employeeId) {
        qualityService.saveQualities(qualities.stream()
                .map(q -> {
                    q.setEmployeeId(employeeId);
                    return q;
                }).collect(Collectors.toList()));
    }
    
    private QualityModel convertToDTO(Quality quality) {
        QualityModel model = new QualityModel();
        model.setDescription(quality.getDescription());
        model.setEmployeeId(quality.getEmployeeId());
        model.setId(quality.getId());
        model.setType(quality.getType());
        return model;
    }
    
        private Quality convertToQuality(QualityModel model) {
        Quality quality = new Quality();
//        quality.setId(model.getId());
//        quality.setEmployeeId(model.getEmployeeId());
        quality.setType(model.getType());
        quality.setDescription(model.getDescription());
        return quality;
    }
}
