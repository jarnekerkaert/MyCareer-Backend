package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/test/employees")
public class PublicEmployeeController {

    private final RestTemplate template;
    private final String URL = "http://localhost:8080/";

    @Autowired
    public PublicEmployeeController(RestTemplate template) {
        this.template = template;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable("id") Long employeeId) {
        Employee emp = template.getForObject(URL+"employees/"+employeeId, Employee.class);
        return emp;
    }
}
