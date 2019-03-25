package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.ValidationException;
import com.realdolmen.mycareer.common.dto.AmbitionModel;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.common.dto.QualityModel;
import com.realdolmen.mycareer.common.dto.RoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
public class PublicEmployeeController {

    private final EmployeeClient employeeClient;
    private final RoleClient roleClient;
    private final QualityClient qualityClient;
    private final AmbitionClient ambitionClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicEmployeeController.class);

    @Autowired
    public PublicEmployeeController(EmployeeClient employeeClient, RoleClient roleClient,
                                    QualityClient qualityClient, AmbitionClient ambitionClient) {
        this.employeeClient = employeeClient;
        this.roleClient = roleClient;
        this.qualityClient = qualityClient;
        this.ambitionClient = ambitionClient;
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeModel getEmployeeById(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
        LOGGER.info("STARTING GET employee with id: {}", employeeId);
        EmployeeModel emp;
        try {
            emp = employeeClient.getEmployee(employeeId);
            LOGGER.info("GET employee with id: {} SUCCESS", employeeId);
        } catch (HttpClientErrorException e) {
            LOGGER.error("{} at GET employee with id: {}", e.getMessage(), employeeId);
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
}
