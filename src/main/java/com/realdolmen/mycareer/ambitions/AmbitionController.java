package com.realdolmen.mycareer.ambitions;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.ValidationException;
import com.realdolmen.mycareer.common.dto.AmbitionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.web.client.HttpServerErrorException;

@RestController
public class AmbitionController {

    private final AmbitionService ambitionService;

    @Autowired
    public AmbitionController(AmbitionService ambitionService) {
        this.ambitionService = ambitionService;
    }

    @RequestMapping(value = "/ambitions", method = RequestMethod.GET)
    public List<Ambition> getAmbitions() {
        return ambitionService.findAll();
    }

    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.GET)
    public List<AmbitionModel> getAmbitionsEmployee(@PathVariable("id") Long employeeId) {
        return ambitionService.findByEmployeeId(employeeId).stream().map(a -> convertToDTO(a)).collect(Collectors.toList());
    }

    @Transactional
    @RequestMapping(value = "employees/{id}/ambitions", method = RequestMethod.DELETE)
    public void deleteAmbitions(@PathVariable("id") Long employeeId) {
        ambitionService.deleteByEmployeeId(employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.POST)
    public void postAmbitions(@PathVariable("id") Long employeeId, @RequestBody List<Ambition> ambitions) {
        saveAmbitions(ambitions, employeeId);
    }

    @Transactional
    @RequestMapping(value = "/employees/{id}/ambitions", method = RequestMethod.PUT)
    public void updateAmbitions(@PathVariable("id") Long employeeId, @Valid @RequestBody List<AmbitionModel> ambitions)
    {
            ambitionService.deleteByEmployeeId(employeeId);
            saveAmbitions(ambitions.stream().map(a -> convertToAmbition(a)).collect(Collectors.toList()),employeeId);
    }

    private void saveAmbitions(List<Ambition> ambitions, Long employeeId) {
        ambitionService.saveAmbitions(ambitions.stream()
                .map(a -> {
                    a.setEmployeeId(employeeId);
                    return a;
                }).collect(Collectors.toList()));
    }

    private AmbitionModel convertToDTO(Ambition ambition) {
        AmbitionModel model = new AmbitionModel();
        model.setTitle(ambition.getTitle());
        model.setMotivation(ambition.getMotivation());
        model.setEmployeeId(ambition.getEmployeeId());
        model.setId(ambition.getId());
        model.setTerm(ambition.getTerm());
        return model;
    }

    private Ambition convertToAmbition(AmbitionModel model) {
        Ambition ambition = new Ambition();
        ambition.setTerm(model.getTerm());
        ambition.setMotivation(model.getMotivation());
        ambition.setTitle(model.getTitle());
        return ambition;
    }
}
