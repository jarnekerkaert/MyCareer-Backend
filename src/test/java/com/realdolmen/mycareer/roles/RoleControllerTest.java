package com.realdolmen.mycareer.roles;

import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

    @Mock
    private RoleService serviceMock;

    private RoleController controller;
    
    private Employee emp;
    private List<Role> roles;
      
    public RoleControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new RoleController(serviceMock);
        
        makeEmployee();
        makeRoles();
    }
            

    private void makeEmployee() {
        emp = new Employee();
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setPassword("plaintext");
        emp.setId(1L);
    }
    
    private void makeRoles(){
        Role role1 = new Role();
        role1.setTitle("title1");
        role1.setDescription("description1");
        role1.setStart(new Date());
        role1.setId(100L);
        role1.setEmployeeId(1L);
        
        Role role2 = new Role();
        role2.setTitle("title1");
        role2.setDescription("description1");
        role2.setStart(new Date());
        role2.setId(100L);
        role2.setEmployeeId(1L);
        
        roles = new ArrayList<>();
        roles.addAll(Arrays.asList(role1,role2));
    }

    @Test
    public void getRolesOfEmployee(){
        controller.getRolesOfEmployee(1L);
        
        Mockito.verify(serviceMock).findByEmployeeId(1L);
    }
    
    @Test
    public void updateRolesOfEmployee(){
        controller.updateRoles(1L, roles);
        
        Mockito.verify(serviceMock).deleteByEmployeeId(1L);
       // Mockito.verify(serviceMock).saveRoles(roles);
      
    }
}
