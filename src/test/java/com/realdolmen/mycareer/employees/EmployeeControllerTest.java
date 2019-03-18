
package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Employee;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.runners.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    
    @Mock
    private EmployeeService serviceMock;

    private EmployeeController controller;
    
    private Employee emp;
    private Employee badEmpDummy;
    private Validator validator;
    private Employee empDummy;
    private Employee updatedEmployeeDummy;

    @Before
    public void setUp() {
        controller = new EmployeeController(serviceMock);
        
        validator = Validation.buildDefaultValidatorFactory().getValidator();
          
        Mockito.when(serviceMock.findById(Mockito.any())).thenReturn(Optional.of(new Employee()));
        
        makeEmployee();
        makeEmployeeDummy();
        makeBadEmployeeDummy();
        makeUpdatedEmployeeDummy();
        
    }
    
    private void makeEmployee(){
        emp = new Employee();
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setCv_filepath("leeg");
        emp.setPassword("plaintext");
        emp.setId(1L);
        controller.createEmployee(emp);
    }
    
    private void makeEmployeeDummy(){
        empDummy = new Employee();
        empDummy.setFirstname("Nathan");
        empDummy.setLastname("Westerlinck");
        empDummy.setEmail("test@test.com");
        empDummy.setBirthdate(new Date());
        empDummy.setCv_filepath("leeg");
        empDummy.setPassword("plaintext");
        empDummy.setId(3L);
    }
    
    private void makeBadEmployeeDummy(){
        badEmpDummy = new Employee();
    }
    
    private void makeUpdatedEmployeeDummy(){
        updatedEmployeeDummy = new Employee();
        updatedEmployeeDummy.setFirstname("Jarne");
        updatedEmployeeDummy.setLastname("Kerkaert");
        updatedEmployeeDummy.setEmail("test@test.com");
        updatedEmployeeDummy.setBirthdate(new Date());
        updatedEmployeeDummy.setCv_filepath("leeg");
        updatedEmployeeDummy.setPassword("plaintext");
    }

    @Test
    public void getEmployee() {
        controller.getEmployee(123L);

        Mockito.verify(serviceMock).findById(123L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getEmployee_handlesNull() {
        Mockito.when(serviceMock.findById(123L)).thenThrow(new ResourceNotFoundException());

        controller.getEmployee(123L);
    }
    
    @Test
    public void createEmployee() {
        controller.createEmployee(empDummy);

        Mockito.verify(serviceMock).save(empDummy);
    }
    
    @Test
    public void createEmployee_badInput() {
        controller.createEmployee(badEmpDummy);

        Set<ConstraintViolation<Employee>> violations = validator.validate(badEmpDummy);
        assertThat(violations.size()).isEqualTo(3);
        
        //verify(serviceMock, never()).save(badEmpDummy);
    }
    
    @Test 
    public void updateEmployee(){
        controller.updateEmployee(1L, updatedEmployeeDummy);
         
         Mockito.verify(serviceMock).save(updatedEmployeeDummy);
         
         Mockito.when(serviceMock.findById(1L)).thenReturn(Optional.of(updatedEmployeeDummy));
         assertEquals(serviceMock.findById(1L).get().getFirstname(),updatedEmployeeDummy.getFirstname());
    }
    
    @Test(expected = ResourceNotFoundException.class)
    public void updateEmployee_NonExistent(){
        Mockito.when(serviceMock.findById(123L)).thenThrow(new ResourceNotFoundException());

        controller.updateEmployee(123L, updatedEmployeeDummy);
    }
}
