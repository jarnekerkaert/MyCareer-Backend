package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Ambition;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Function;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.domain.Quality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
    public EmployeeModel getEmployeeById(@PathVariable("id") Long employeeId) throws ResourceNotFoundException{
        try {
            Employee emp = template.getForObject(URL + "employees/" + employeeId, Employee.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
       
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
    public void createEmployee(@RequestBody EmployeeModel emp) throws IllegalArgumentException {
        try{
          template.postForObject(URL+"employees", emp, Employee.class);
        }
        catch(Exception e){
            throw new IllegalArgumentException("Input must be valid!");
        }
//        template.postForObject(URL+"employees/"+employeeId+"/functions", emp.getFunctions(), ResponseEntity.class);
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
        template.put(URL+"employees/"+employeeId+"/functions", emp.getFunctions());
        template.put(URL+"employees/"+employeeId+"/qualities", emp.getQualities());
        template.put(URL+"employees/"+employeeId+"/ambitions", emp.getAmbitions());
    }

    private EmployeeModel convertToDTO(Long employeeId, List<Function> functions, List<Quality> qualities,
            List<Ambition> ambitions) {
        Employee employee = template.getForObject(URL + "employees/" + employeeId, Employee.class);
        EmployeeModel model = new EmployeeModel(employee);
        model.setFunctions(functions);
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
