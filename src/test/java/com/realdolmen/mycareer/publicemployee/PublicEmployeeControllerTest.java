package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.dto.EmployeeModel;
import com.realdolmen.mycareer.employees.Employee;
import com.realdolmen.mycareer.employees.EmployeeController;
import java.util.Date;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class PublicEmployeeControllerTest {

    //private EmployeeController employeeController;
    //private RoleController roleController;
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private EmployeeController employeeController;

    private Employee emp;

    @Before
    public void before() {
        emp = new Employee();
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setCv_filepath("leeg");
        emp.setPassword("plaintext");
        emp.setId(1L);
    }

    public PublicEmployeeControllerTest() {

    }
    
    @Test
    public void getEmployee() throws Exception {
        // mock mvc gebruiken
        
    }

}
