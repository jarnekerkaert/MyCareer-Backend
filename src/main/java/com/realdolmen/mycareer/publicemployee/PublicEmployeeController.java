package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.ValidationException;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.domain.Ambition;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Quality;
import com.realdolmen.mycareer.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/employees")
public class PublicEmployeeController {

    private final RestTemplate template;

    @Value("${app.backend.url}")
    private String url;

    private final String serviceUrl = "employees/";

    @Autowired
    public PublicEmployeeController(RestTemplate template) {
        this.template = template;
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeModel getEmployeeById(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
        try {
            Employee emp = template.getForObject(url + serviceUrl + employeeId, Employee.class);

            ResponseEntity<List<Role>> roles = template.exchange(url + serviceUrl + employeeId + "/roles",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Role>>() {
                    });
            ResponseEntity<List<Quality>> qualities = template.exchange(url + serviceUrl + employeeId + "/qualities",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Quality>>() {
                    });
            ResponseEntity<List<Ambition>> ambitions = template.exchange(url + serviceUrl + employeeId + "/ambitions",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Ambition>>() {
                    });
            return this.convertToDTO(emp, roles.getBody(), qualities.getBody(), ambitions.getBody());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createEmployee(@RequestBody Employee emp) throws ValidationException {
        try {
            template.postForObject(url + serviceUrl, emp, Employee.class);
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            e.printStackTrace();
            throw new ValidationException("Something went wrong...");
        }
//        template.postForObject(URL+"employees/"+employeeId+"/roles", emp.getRoles(), ResponseEntity.class);
//        template.postForObject(URL+"employees/"+employeeId+"/qualities", emp.getQualities(), ResponseEntity.class);
//        template.postForObject(URL+"employees/"+employeeId+"/ambitions", emp.getAmbitions(), ResponseEntity.class);
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeModel emp) throws Exception {
        // CHECK IF EMPLOYEE EXISTS
//        try {
//            Employee getEmp = template.getForObject(url + serviceUrl + employeeId, Employee.class);
//        } catch (HttpClientErrorException e) {
//            e.printStackTrace();
//            throw new ResourceNotFoundException("Employee", "id", employeeId);
//        }
        try {
            template.put(url + serviceUrl + employeeId, convertToEmployee(employeeId, emp));
            template.put(url + serviceUrl + employeeId + "/roles", emp.getRoles());
            template.put(url + serviceUrl + employeeId + "/qualities", emp.getQualities());
            template.put(url + serviceUrl + employeeId + "/ambitions", emp.getAmbitions());
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            e.printStackTrace();
            throw new ValidationException("Something went wrong...");
        }
    }

    private EmployeeModel convertToDTO(Employee employee, List<Role> roles, List<Quality> qualities,
                                       List<Ambition> ambitions) {
        EmployeeModel model = new EmployeeModel(employee);
        model.setRoles(roles);
        model.setQualities(qualities);
        model.setAmbitions(ambitions);
        return model;
    }

    private Employee convertToEmployee(Long id, EmployeeModel model) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setFirstname(model.getFirstname());
        emp.setLastname(model.getLastname());
        emp.setEmail(model.getEmail());
        emp.setBirthdate(model.getBirthdate());
        return emp;
    }

}
