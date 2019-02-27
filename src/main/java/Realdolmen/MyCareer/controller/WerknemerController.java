package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.domain.Werknemer;
import Realdolmen.MyCareer.service.IWerknemerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WerknemerController {
    @Autowired
    IWerknemerService werknemerService;
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "test";
    }
    
    @RequestMapping(value = "/werknemer/{id}", method = RequestMethod.GET)
    public Werknemer getWerknemer(@PathVariable("id") Long werknemerId) {
        // return userRepository.findById(userId);
        return werknemerService.findWerknemerById(werknemerId);
    }
}
