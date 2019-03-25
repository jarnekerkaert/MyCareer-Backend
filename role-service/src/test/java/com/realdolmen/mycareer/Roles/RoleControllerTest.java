package com.realdolmen.mycareer.Roles;

import com.realdolmen.mycareer.common.dto.RoleModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RoleControllerTest {

    @Mock
    private RoleService serviceMock;
    private RoleController controller;

    private List<Role> roles;
    private List<RoleModel> roleModels;
      
    public RoleControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new RoleController(serviceMock);
        
        roles = makeRoles();
        roleModels = makeRoleModels();
    }

    @Test
    public void getRolesOfEmployee(){
        controller.getRolesOfEmployee(1L);
        
        Mockito.verify(serviceMock).findByEmployeeId(1L);
    }
    
    @Test
    public void updateRolesOfEmployee(){
        controller.updateRoles(1L, roleModels);
        
        Mockito.verify(serviceMock).deleteByEmployeeId(1L);
        Mockito.verify(serviceMock).saveRoles(ArgumentMatchers.refEq(roles));
    }

    private List<Role> makeRoles(){
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

        return new ArrayList<>(Arrays.asList(role1, role2));
    }

    private List<RoleModel> makeRoleModels() {
        RoleModel role1 = new RoleModel();
        role1.setTitle("title1");
        role1.setDescription("description1");
        role1.setStart(new Date());
        role1.setId(100L);
        role1.setEmployeeId(1L);

        RoleModel role2 = new RoleModel();
        role2.setTitle("title1");
        role2.setDescription("description1");
        role2.setStart(new Date());
        role2.setId(100L);
        role2.setEmployeeId(1L);

        return new ArrayList<>(Arrays.asList(role1, role2));
    }
}
