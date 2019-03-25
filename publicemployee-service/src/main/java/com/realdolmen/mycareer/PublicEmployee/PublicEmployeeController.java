package com.realdolmen.mycareer.PublicEmployee;

import com.realdolmen.mycareer.CommonLibrary.common.ResourceNotFoundException;
import com.realdolmen.mycareer.CommonLibrary.common.ValidationException;
import com.realdolmen.mycareer.CommonLibrary.common.dto.AmbitionModel;
import com.realdolmen.mycareer.CommonLibrary.common.dto.EmployeeModel;
import com.realdolmen.mycareer.CommonLibrary.common.dto.QualityModel;
import com.realdolmen.mycareer.CommonLibrary.common.dto.RoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
public class PublicEmployeeController {

    private final RestTemplate template;

    private static Logger logger = LoggerFactory.getLogger(PublicEmployeeController.class);

    @Value("${app.backend.url}")
    private String url;

    private final Environment environment;

    private final String serviceUrl = "employees";

    @Autowired
    public PublicEmployeeController(RestTemplate template, Environment environment) {
        this.template = template;
        this.environment = environment;
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeModel getEmployeeById(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
        EmployeeModel emp;
        try {
            emp = template.getForObject(buildUrl(url, environment.getProperty("app.backend.employee.port"), serviceUrl)
                     + employeeId, EmployeeModel.class);
        } catch (HttpClientErrorException e) {
            logger.error("Resource not found", new ResourceNotFoundException("Employee", "id", employeeId));
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }

        ResponseEntity<List<RoleModel>> roles = template.exchange(buildUrl(url, environment.getProperty("app.backend.role.port")
                        , serviceUrl) + employeeId + "/roles",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RoleModel>>() {
                });
        ResponseEntity<List<QualityModel>> qualities = template.exchange(buildUrl(url, environment.getProperty("app.backend.quality.port")
                , serviceUrl) + employeeId + "/qualities",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<QualityModel>>() {
                });
        ResponseEntity<List<AmbitionModel>> ambitions = template.exchange(buildUrl(url, environment.getProperty("app.backend.ambition.port")
                , serviceUrl) + employeeId + "/ambitions",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<AmbitionModel>>() {
                });

        return this.fillDTO(emp, roles.getBody(), qualities.getBody(), ambitions.getBody());
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createEmployee(@RequestBody EmployeeModel emp) throws ValidationException {
        try {
            template.postForObject(buildUrl(url, environment.getProperty("app.backend.employee.port")
                    , serviceUrl), emp, EmployeeModel.class);
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            e.printStackTrace();
            throw new ValidationException("Something went wrong...");
        }
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeModel emp) throws Exception {

//        try {
//            EmployeeModel getEmp = template.getForObject(buildUrl(url, environment.getProperty("app.backend.employee.port")
//                    , serviceUrl) + employeeId, EmployeeModel.class);
//        } catch (HttpClientErrorException e) {
//            e.printStackTrace();
//            throw new ResourceNotFoundException("Employee", "id", employeeId);
//        }

        try {
            template.put(buildUrl(url, environment.getProperty("app.backend.employee.port")
                    , serviceUrl) + employeeId, emp);
            template.put(buildUrl(url, environment.getProperty("app.backend.role.port")
                    , serviceUrl) + employeeId + "/roles", emp.getRoles());
            template.put(buildUrl(url, environment.getProperty("app.backend.quality.port")
                    , serviceUrl) + employeeId + "/qualities", emp.getQualities());
            template.put(buildUrl(url, environment.getProperty("app.backend.ambition.port")
                    , serviceUrl) + employeeId + "/ambitions", emp.getAmbitions());
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            e.printStackTrace();
            throw new ValidationException("Something went wrong...");
        }
    }

    private EmployeeModel fillDTO(EmployeeModel emp, List<RoleModel> roles, List<QualityModel> qualities,
                                 List<AmbitionModel> ambitions) {
        emp.setRoles(roles);
        emp.setAmbitions(ambitions);
        emp.setQualities(qualities);
        return emp;
    }

    private String buildUrl(String host, String port, String service) {
        StringBuilder builder = new StringBuilder();
        builder.append(host)
                .append(":")
                .append(port)
                .append("/")
                .append(service)
                .append("/");
        return builder.toString();
    }
}
