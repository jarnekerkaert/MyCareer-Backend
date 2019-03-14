package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.domain.Ambition;
import com.realdolmen.mycareer.common.domain.Employee;
import com.realdolmen.mycareer.common.domain.Function;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.common.domain.Quality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/test/employees")
public class PublicEmployeeController {

    private final RestTemplate template;
    private final String URL = "http://localhost:8080/";

    @Autowired
    public PublicEmployeeController(RestTemplate template) {
        this.template = template;
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeModel getEmployeeById(@PathVariable("id") Long employeeId) {
        ResponseEntity<List<Function>> functions = template.exchange(URL + "employees/" + employeeId + "/functions",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Function>>() {});
        ResponseEntity<List<Quality>> qualities = template.exchange(URL + "employees/" + employeeId + "/qualities",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Quality>>() {});
        ResponseEntity<List<Ambition>> ambitions = template.exchange(URL + "employees/" + employeeId + "/ambitions",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ambition>>() {});

        return this.convertToDTO(employeeId, functions.getBody(), qualities.getBody(), ambitions.getBody());
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void postEmployee(@Valid @RequestBody Employee emp) {

        template.postForObject(URL+"employees", emp, Employee.class);
    }

    @RequestMapping(value = "/{id}/functions", method = RequestMethod.GET)
    public List<Function> getFunctionsByEmployeeId(@PathVariable("id") Long id) {
        ResponseEntity<List<Function>> response = template.exchange(URL + "employees/" + id + "/functions",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Function>>() {});
        return response.getBody();
    }

    private EmployeeModel convertToDTO(Long employeeId, List<Function> functions, List<Quality> qualities,
                                       List<Ambition> ambitions) {
        Employee employee = template.getForObject(URL+"employees/"+employeeId, Employee.class);
        EmployeeModel model = new EmployeeModel(employee);
        model.setFunctions(functions);
        model.setQualities(qualities);
        model.setAmbitions(ambitions);
        return model;
    }
}
