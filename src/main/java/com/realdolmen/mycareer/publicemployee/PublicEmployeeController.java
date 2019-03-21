package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.ValidationException;
import com.realdolmen.mycareer.common.dto.AmbitionModel;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.common.dto.QualityModel;
import com.realdolmen.mycareer.common.dto.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping(value = "/api/employees")
public class PublicEmployeeController {

    private final RestTemplate template;
    private final String URL = "http://localhost:8080/";

    @Autowired
    public PublicEmployeeController(RestTemplate template) {
        this.template = template;
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeModel getEmployeeById(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
        EmployeeModel emp;
        try {
            emp = template.getForObject(URL + "employees/" + employeeId, EmployeeModel.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }

        ResponseEntity<List<RoleModel>> roles = template.exchange(URL + "employees/" + employeeId + "/roles",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RoleModel>>() {
        });
        ResponseEntity<List<QualityModel>> qualities = template.exchange(URL + "employees/" + employeeId + "/qualities",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<QualityModel>>() {
        });
        ResponseEntity<List<AmbitionModel>> ambitions = template.exchange(URL + "employees/" + employeeId + "/ambitions",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<AmbitionModel>>() {       
        });

        return this.fillDTO(emp,roles.getBody(),qualities.getBody(),ambitions.getBody());
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createEmployee(@RequestBody EmployeeModel emp) throws ValidationException {
        try {
            template.postForObject(URL + "employees", emp, EmployeeModel.class);
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

        try {
            EmployeeModel getEmp = template.getForObject(URL + "employees/" + employeeId, EmployeeModel.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }

        try {
            template.put(URL + "employees/" + employeeId, emp);
            template.put(URL + "employees/" + employeeId + "/roles", emp.getRoles());
            template.put(URL + "employees/" + employeeId + "/qualities", emp.getQualities());
            template.put(URL + "employees/" + employeeId + "/ambitions", emp.getAmbitions());
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            e.printStackTrace();
            throw new ValidationException("Something went wrong...");
        }

    }
    
    public EmployeeModel fillDTO(EmployeeModel emp, List<RoleModel> roles, List<QualityModel> qualities,
            List<AmbitionModel> ambitions){
        emp.setRoles(roles);
        emp.setAmbitions(ambitions);
        emp.setQualities(qualities);
        return emp;
    }

}
