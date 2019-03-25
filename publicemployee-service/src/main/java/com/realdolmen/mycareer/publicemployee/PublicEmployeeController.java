package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.commonlibrary.common.ResourceNotFoundException;
import com.realdolmen.mycareer.commonlibrary.common.ValidationException;
import com.realdolmen.mycareer.commonlibrary.common.dto.AmbitionModel;
import com.realdolmen.mycareer.commonlibrary.common.dto.EmployeeModel;
import com.realdolmen.mycareer.commonlibrary.common.dto.QualityModel;
import com.realdolmen.mycareer.commonlibrary.common.dto.RoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

    private final EmployeeClient employeeClient;
    private final RoleClient roleClient;
    private final QualityClient qualityClient;
    private final AmbitionClient ambitionClient;

    private static Logger logger = LoggerFactory.getLogger(PublicEmployeeController.class);

    @Value("${app.backend.url}")
    private String url;

    private final Environment environment;

    private final String serviceUrl = "employees";

    @Autowired
    public PublicEmployeeController(RestTemplate template, EmployeeClient employeeClient, RoleClient roleClient, QualityClient qualityClient, AmbitionClient ambitionClient, Environment environment) {
        this.template = template;
        this.employeeClient = employeeClient;
        this.roleClient = roleClient;
        this.qualityClient = qualityClient;
        this.ambitionClient = ambitionClient;
        this.environment = environment;
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeModel getEmployeeById(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
        EmployeeModel emp;
        try {
            emp = employeeClient.getEmployee(employeeId);

        } catch (HttpClientErrorException e) {
            logger.error("Resource not found", new ResourceNotFoundException("Employee", "id", employeeId));
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }

        List<RoleModel> roles = roleClient.getRolesOfEmployee(employeeId);
        List<QualityModel> qualities = qualityClient.getAllQualitiesEmployee(employeeId);
        List<AmbitionModel> ambitions = ambitionClient.getAmbitionsEmployee(employeeId);

        return this.fillDTO(emp, roles, qualities, ambitions);
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createEmployee(@RequestBody EmployeeModel emp) throws ValidationException {
        try {
            employeeClient.createEmployee(emp);
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            e.printStackTrace();
            throw new ValidationException("Something went wrong...");
        }
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeModel emp) throws Exception {
        try {
            employeeClient.updateEmployee(employeeId,emp);
            roleClient.updateRoles(employeeId,emp.getRoles());
            qualityClient.updateQualities(employeeId, emp.getQualities());
            ambitionClient.updateAmbitions(employeeId, emp.getAmbitions());
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
