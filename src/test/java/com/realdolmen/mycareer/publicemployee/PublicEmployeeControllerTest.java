package com.realdolmen.mycareer.publicemployee;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class PublicEmployeeControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PublicEmployeeController controller;

    private EmployeeModel emp;

    @Before
    public void before() {
        emp = makeEmployeeModelNoLists();
    }

    @Test
    public void getEmployeeById_IdNull_ReturnNull() {
        EmployeeModel model = controller.getEmployeeById(null);
        Assert.assertNull(model);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getEmployeeById_IdNotExists_ResourceNotFound() {
        Mockito.when(controller.getEmployeeById(666L));
        EmployeeModel model = controller.getEmployeeById(666L);

    }

    @Test
    public void createEmployee() {

    }

    @Test
    public void updateEmployee() {

    }

    private EmployeeModel makeEmployeeModelNoLists() {
        EmployeeModel emp = new EmployeeModel();
        emp.setId(1L);
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        return emp;
    }
}
