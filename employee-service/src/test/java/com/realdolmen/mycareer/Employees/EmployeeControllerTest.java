
package com.realdolmen.mycareer.Employees;

import com.realdolmen.mycareer.CommonLibrary.common.ResourceNotFoundException;
import com.realdolmen.mycareer.CommonLibrary.common.dto.EmployeeModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    
    @Mock
    private EmployeeService serviceMock;

    private EmployeeController controller;
    
    private Employee employee;
    private EmployeeModel employeeModel;
    private Employee badEmployee;
    private EmployeeModel badEmployeeModel;
    private Employee updatedEmployee;
    private EmployeeModel updatedEmployeeModel;
    private Validator validator;

    @Before
    public void setUp() {
        controller = new EmployeeController(serviceMock);
        
        validator = Validation.buildDefaultValidatorFactory().getValidator();
          
        Mockito.when(serviceMock.findById(Mockito.any())).thenReturn(Optional.of(new Employee()));

        employee = makeEmployee();
        employeeModel = makeEmployeeModel();
        badEmployee = makeBadEmployee();
        badEmployeeModel = makeBadEmployeeModel();
        updatedEmployee = makeUpdatedEmployee();
        updatedEmployeeModel = makeUpdatedEmployeeModel();
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
        employeeModel = makeEmployeeModel();
        controller.createEmployee(employeeModel);

        Mockito.verify(serviceMock).save(employee);
    }
    
    @Test
    public void createEmployee_badInput_throwException() {
        controller.createEmployee(badEmployeeModel);

        Set<ConstraintViolation<Employee>> violations = validator.validate(badEmployee);
        assertThat(violations.size()).isEqualTo(3);
}
    
    @Test 
    public void updateEmployee(){
        controller.updateEmployee(1L, updatedEmployeeModel);
         
         Mockito.verify(serviceMock).save(updatedEmployee);
         
         Mockito.when(serviceMock.findById(1L)).thenReturn(Optional.of(updatedEmployee));
         assertEquals(serviceMock.findById(1L).get().getFirstname(),updatedEmployee.getFirstname());
    }
    
    @Test(expected = ResourceNotFoundException.class)
    public void updateEmployee_NonExistent(){
        Mockito.when(serviceMock.findById(123L)).thenThrow(new ResourceNotFoundException());

        controller.updateEmployee(123L, updatedEmployeeModel);
    }

    private Employee makeEmployee(){
        Employee emp = new Employee();
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setPassword("plaintext");
        emp.setId(1L);
        return emp;
    }

    private EmployeeModel makeEmployeeModel(){
        EmployeeModel emp = new EmployeeModel();
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setId(1L);
        return emp;
    }

    private Employee makeBadEmployee(){
        return new Employee();
    }

    private EmployeeModel makeBadEmployeeModel(){
        return new EmployeeModel();
    }

    private Employee makeUpdatedEmployee(){
        Employee emp = new Employee();
        emp.setFirstname("Jarne");
        emp.setLastname("Kerkaert");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setPassword("plaintext");
        return emp;
    }

    private EmployeeModel makeUpdatedEmployeeModel(){
        EmployeeModel emp = new EmployeeModel();
        emp.setFirstname("Jarne");
        emp.setLastname("Kerkaert");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        return emp;
    }
}
