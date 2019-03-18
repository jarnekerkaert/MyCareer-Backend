package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Ambition;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Role;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.domain.Quality;
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
    public EmployeeModel getEmployeeById(@PathVariable("id") Long employeeId) throws ResourceNotFoundException{
        try {
            Employee emp = template.getForObject(URL + "employees/" + employeeId, Employee.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
       
        ResponseEntity<List<Role>> roles = template.exchange(URL + "employees/" + employeeId + "/roles",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Role>>() {});
        ResponseEntity<List<Quality>> qualities = template.exchange(URL + "employees/" + employeeId + "/qualities",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Quality>>() {});
        ResponseEntity<List<Ambition>> ambitions = template.exchange(URL + "employees/" + employeeId + "/ambitions",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ambition>>() {});

        return this.convertToDTO(employeeId, roles.getBody(), qualities.getBody(), ambitions.getBody());
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createEmployee(@RequestBody Employee emp) {
          template.postForObject(URL+"employees", emp, Employee.class);
//        template.postForObject(URL+"employees/"+employeeId+"/roles", emp.getRoles(), ResponseEntity.class);
//        template.postForObject(URL+"employees/"+employeeId+"/qualities", emp.getQualities(), ResponseEntity.class);
//        template.postForObject(URL+"employees/"+employeeId+"/ambitions", emp.getAmbitions(), ResponseEntity.class);
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeModel emp) throws ResourceNotFoundException{

        try {
            Employee getEmp = template.getForObject(URL + "employees/" + employeeId, Employee.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
        
        template.put(URL+"employees/"+employeeId, convertToEmployee(Optional.of(employeeId),emp));
        template.put(URL+"employees/"+employeeId+"/roles", emp.getRoles());
        template.put(URL+"employees/"+employeeId+"/qualities", emp.getQualities());
        template.put(URL+"employees/"+employeeId+"/ambitions", emp.getAmbitions());
    }

    private EmployeeModel convertToDTO(Long employeeId, List<Role> roles, List<Quality> qualities,
            List<Ambition> ambitions) {
        Employee employee = template.getForObject(URL + "employees/" + employeeId, Employee.class);
        EmployeeModel model = new EmployeeModel(employee);
        model.setRoles(roles);
        model.setQualities(qualities);
        model.setAmbitions(ambitions);
        return model;
    }
    
    private Employee convertToEmployee(Optional<Long> id, EmployeeModel model) {
        Employee emp = new Employee();

        emp.setId(id.get());
        emp.setFirstname(model.getFirstname());
        emp.setLastname(model.getLastname());
        emp.setEmail(model.getEmail());
        emp.setBirthdate(model.getBirthdate());
        emp.setCv_filepath(model.getCv_filepath());

        return emp;
    }
   
}
