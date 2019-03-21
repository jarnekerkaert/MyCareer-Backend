
package com.realdolmen.mycareer.qualities;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.dto.QualityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
        return qualityService.findByEmployeeId(employeeId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.POST)
    public void postQualities(@PathVariable("id") Long employeeId, @RequestBody List<Quality> qualities) {
        saveQualities(qualities, employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/qualities", method = RequestMethod.PUT)
    public void updateQualities(@PathVariable("id") Long employeeId, @Valid @RequestBody List<QualityModel> qualities) {
        qualityService.deleteByEmployeeId(employeeId);
        saveQualities(qualities.stream().map(this::convertToQuality).collect(Collectors.toList()), employeeId);
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
                .peek(q -> q.setEmployeeId(employeeId)).collect(Collectors.toList()));
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
        quality.setType(model.getType());
        quality.setDescription(model.getDescription());
        return quality;
    }
}
