package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.ValidationException;
import com.realdolmen.mycareer.ambitions.Ambition;
import com.realdolmen.mycareer.common.QualityType;
import com.realdolmen.mycareer.common.dto.AmbitionModel;
import com.realdolmen.mycareer.employees.Employee;
import com.realdolmen.mycareer.roles.Role;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.common.dto.QualityModel;
import com.realdolmen.mycareer.common.dto.RoleModel;
import com.realdolmen.mycareer.qualities.Quality;
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
        try {
            Employee emp = template.getForObject(URL + "employees/" + employeeId, Employee.class);
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
//          ResponseEntity<List<Ambition>> ambitions = template.exchange(URL + "employees/" + employeeId + "/ambitions",
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ambition>>() {          
        });

        return this.convertToEmployeeDTO(employeeId, roles.getBody(), qualities.getBody(), ambitions.getBody());
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createEmployee(@RequestBody Employee emp) throws ValidationException {
        try {
            template.postForObject(URL + "employees", emp, Employee.class);
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
            Employee getEmp = template.getForObject(URL + "employees/" + employeeId, Employee.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }

        try {
            template.put(URL + "employees/" + employeeId, convertToEmployee(Optional.of(employeeId), emp));
            template.put(URL + "employees/" + employeeId + "/roles", emp.getRoles().stream().map(r -> convertToRole(r)));
            template.put(URL + "employees/" + employeeId + "/qualities", emp.getQualities().stream().map(q -> convertToQuality(q)));
            template.put(URL + "employees/" + employeeId + "/ambitions", emp.getAmbitions().stream().map(a -> convertToAmbition(a)));
            //template.put(URL + "employees/" + employeeId + "/ambitions", emp.getAmbitions());
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            e.printStackTrace();
            throw new ValidationException("Something went wrong...");
        }

    }

    private EmployeeModel convertToEmployeeDTO(Long employeeId, List<RoleModel> roles, List<QualityModel> qualities,
            List<AmbitionModel> ambitions) {
        //List<Ambition> ambitions) {
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

    private RoleModel convertToRoleDTO(Role role) {
        RoleModel model = new RoleModel(role);
        return model;
    }

    private Role convertToRole(RoleModel model) {
        Role role = new Role();
//        role.setId(model.getId());
//        role.setEmployeeId(model.getEmployeeId());
        role.setTitle(model.getTitle());
        role.setDescription(model.getDescription());
        role.setStart(model.getStart());
        role.setEnding(model.getEnding());
        return role;
    }

    private QualityModel convertToQualityDTO(Quality quality) {
        QualityModel model = new QualityModel(quality);
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

    private AmbitionModel convertToAmbitionDTO(Ambition ambition) {
        AmbitionModel model = new AmbitionModel(ambition);
        return model;
    }

    private Ambition convertToAmbition(AmbitionModel model) {
        Ambition ambition = new Ambition();
        ambition.setTerm(model.getTerm());
        ambition.setMotivation(model.getMotivation());
        ambition.setTitle(model.getTitle());
//        ambition.setId(model.getId());
        return ambition;
    }
}
