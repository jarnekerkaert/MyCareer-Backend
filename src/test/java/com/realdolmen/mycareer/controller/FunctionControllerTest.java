//package com.realdolmen.mycareer.controller;
//
//import com.realdolmen.mycareer.domain.Employee;
//import com.realdolmen.mycareer.employees.EmployeeService;
//import com.realdolmen.mycareer.domain.Role;
//import com.realdolmen.mycareer.roles.RoleController;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.realdolmen.mycareer.roles.RoleService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.*;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(RoleController.class)
//public class RoleControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private EmployeeService service;
//
//    @MockBean
//    RoleService roleService;
//
//    private Employee empDummy;
//    private List<Role> listRoles = new ArrayList<>();
//    private List<Role> listCurrentRoles = new ArrayList<>();
//    private List<Role> listPrevRoles = new ArrayList<>();
//    private Role f1 = new Role(), f2 = new Role(),
//            f3 = new Role();
//
//    @Before
//    public void before() {
//        // to get employee by id
//        empDummy = new Employee();
//        empDummy.setFirstname("Nathan");
//        empDummy.setLastname("Westerlinck");
//        empDummy.setEmail("test@test.com");
//        empDummy.setBirthdate(new Date());
//        empDummy.setCv_filepath("leeg");
//        empDummy.setPassword("plaintext");
//        empDummy.setId(1L);
//
//        // post list of roles:
//        f1.setTitle("title1");
//        f1.setDescription("description1");
//        f1.setId(777L);
//        //f1.setEmployee(empDummy);
//        f2.setTitle("title2");
//        f2.setDescription("description2");
//        //f2.setEmployee(empDummy);
//        f3.setTitle("title3");
//        f3.setDescription("description3");
//        f3.setStart(new Date());
//        f3.setEnding(new Date());
//        //f3.setEmployee(empDummy);
//
//        listRoles.addAll(Arrays.asList(f1, f2, f3));
//        listCurrentRoles.addAll(Arrays.asList(f1, f2));
//        listPrevRoles.add(f3);
//    }
//
//    // ----------------------------------------------------------------------------------------------------------------------------------------------
//    // ROLE - POST
//
//    /**
//     * Test for adding a current role
//     * @throws Exception
//     */
//    /*
//    @Test
//    public void createCurrentRole() throws Exception {
//        String uri = "/employee/postcurrentrole/1";
//
//        createEmployee();
//        given(roleService.save(currentrole)).willReturn(currentrole);
//
//        System.out.println(currentrole);
//        mvc.perform(post(uri)
//                .contentType(APPLICATION_JSON)
//                .content(mapToJson(currentrole)))
//                //.content(asJsonString(role)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("description", is(currentrole.getDescription())))
//                .andExpect(jsonPath("title", is(currentrole.getTitle())));
//    }
//
//    @Test
//    public void createCurrentRoleBadEmployee() throws Exception {
//        String uri = "/employee/postcurrentrole/155";
//
//        createEmployee();
//        given(roleService.save(currentrole)).willReturn(currentrole);
//
//        System.out.println(currentrole);
//        mvc.perform(post(uri)
//                .contentType(APPLICATION_JSON)
//                .content(mapToJson(currentrole)))
//                //.content(asJsonString(role)))
//                .andExpect(status().isNotFound())
//                ;
//    }*/
//
//    /**
//     * Test for adding a previous role
//     * @throws Exception
//     */
//    /*
//    @Test
//    // TODO: datums checken !!
//    public void createPreviousRole() throws Exception {
//        String uri = "/employee/postpreviousrole/1";
//
//        createEmployee();
//        given(roleService.save(prevrole)).willReturn(prevrole);
//
//        mvc.perform(post(uri)
//                .contentType(APPLICATION_JSON)
//                .content(mapToJson(prevrole)))
//                //.content(asJsonString(role)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("description", is(prevrole.getDescription())))
//                .andExpect(jsonPath("start", is(prevrole.getStart())))
//                .andExpect(jsonPath("ending", is(prevrole.getEnding())))
//                .andExpect(jsonPath("title", is(prevrole.getTitle())));
//    }*/
//    /*
//    @Test
//    // TODO: datums checken !!
//    public void createPreviousRoleBadEmployee() throws Exception {
//        String uri = "/employee/postpreviousrole/551";
//
//        createEmployee();
//        given(roleService.save(prevrole)).willReturn(prevrole);
//
//        mvc.perform(post(uri)
//                .contentType(APPLICATION_JSON)
//                .content(mapToJson(prevrole)))
//                //.content(asJsonString(role)))
//                .andExpect(status().isNotFound());
//    }*/
//
//    /**
//     * Test for adding a list of current roles
//     * @throws Exception
//     */
//    /*
//    @Test
//    public void postListOfCurrentRoles() throws Exception {
//        String uri = "/employee/postcurrentroles/1";
//
//        createEmployee();
//        given(roleService.saveListOfCurrentRoles(listCurrentroles)).willReturn(listCurrentroles); // returnt eigenlijk een array van current roles
//
//        mvc.perform(post(uri)
//                .contentType(APPLICATION_JSON)
//                .content(mapToJson(listCurrentroles)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title", is("title1")))
//                .andExpect(jsonPath("$[0].description", is("description1")))
//                //.andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[1].title", is("title2")))
//                .andExpect(jsonPath("$[1].description", is("description2")))
//                //.andExpect(jsonPath("$[1].id", is(2)))
//               ;
//    }*/
//
//    /**
//     *  Test for adding a list of previous roles
//     * @throws Exception
//     */
//    /*
//    @Test
//    public void postListOfPreviousRoles() throws Exception {
//        String uri = "/employee/postpreviousroles/1";
//
//        createEmployee();
//        given(roleService.saveListOfPrevRoles(listPrevroles)).willReturn(listPrevroles); // returnt eigenlijk een array van previous roles
//
//        mvc.perform(post(uri)
//                .contentType(APPLICATION_JSON)
//                .content(mapToJson(listPrevroles)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title", is("abc")))
//                .andExpect(jsonPath("$[0].description", is("def")))
//                //.andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[1].title", is("ghi")))
//                .andExpect(jsonPath("$[1].description", is("jkl")))
//                //.andExpect(jsonPath("$[1].id", is(2)))
//               ;
//    }*/
//
//    /**
//     * Test for adding a list of current roles and a list of previous roles
//     *
//     * @throws Exception
//     */
//    @Test
//    public void postRoles() throws Exception {
//        String uri = "/employees/1/roles";
//
//        createEmployee();
//        //given(roleService.saveTwoListsOfRoles(listCurrentroles,listPrevroles)).willReturn();
//
//        assertNotEquals(roleService.findCurrentRoles(1L), listCurrentRoles);
//        assertNotEquals(roleService.findPrevRoles(1L), listPrevRoles);
//
//        mvc.perform(post(uri)
//                .contentType(APPLICATION_JSON)
//                .content(mapToJson(listRoles))
//        )
//                .andExpect(status().isOk());
//
//        verify(roleService, Mockito.times(1)).saveRoles(listRoles);
//
//        given(roleService.findCurrentRoles(1L)).willReturn(listCurrentRoles);
//        Role curf1 = (Role) roleService.findCurrentRoles(1L).get(0);
//        Role curf2 = (Role) roleService.findCurrentRoles(1L).get(1);
//
//        given(roleService.findPrevRoles(1L)).willReturn(listPrevRoles);
//        Role prevf = (Role) roleService.findPrevRoles(1L).get(0);
//        assertEquals(listRoles.get(0).getTitle(), curf1.getTitle());
//        assertEquals(listRoles.get(1).getTitle(), curf2.getTitle());
//        assertEquals(listRoles.get(2).getTitle(), prevf.getTitle());
//
//        assertEquals(roleService.findCurrentRoles(1L), listCurrentRoles);
//        assertEquals(roleService.findPrevRoles(1L), listPrevRoles);
//    }
//
//    @Test
//    public void postRolesBadEmployee() throws Exception {
//        String uri = "/employees/155/roles";
//
//        createEmployee();
//        //given(roleService.saveTwoListsOfRoles(listCurrentroles,listPrevroles)).willReturn();
//
//        mvc.perform(post(uri)
//                        .contentType(APPLICATION_JSON)
//                        .content(mapToJson(listRoles))
//                //.content(mapToJson(roleWrapper))
//        ).andExpect(status().isNotFound());
//
//        verify(roleService, never()).saveRoles(listRoles);
//    }
//// ----------------------------------------------------------------------------------------------------------------------------------------------
//
//    // ROLE - GET
//
//    @Test
//    public void getCurrentRolesOfEmployee() throws Exception {
//
//        createEmployee();
//
//        //roleService.saveRoles(listRoles);
//        given(roleService.findCurrentRoles(1L)).willReturn(listCurrentRoles);
//
//        mvc.perform(get("/employees/1/currentroles")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title", is("title1")))
//                .andExpect(jsonPath("$[0].description", is("description1")))
//        ;
//    }
//
//    @Test
//    public void getCurrentRolesOfBadEmployee() throws Exception {
//
//        createEmployee();
//
//        given(roleService.findCurrentRoles(1L)).willReturn(listCurrentRoles);
//
//        mvc.perform(get("/employees/1555/currentroles")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//        ;
//    }
//
//    @Test
//    public void getPreviousRolesOfEmployee() throws Exception {
//
//        createEmployee();
//
//        given(roleService.findPrevRoles(1L)).willReturn(listPrevRoles);
//
//        mvc.perform(get("/employees/1/prevroles")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title", is("title3")))
//                .andExpect(jsonPath("$[0].description", is("description3")))
//        ;
//    }
//
//    @Test
//    public void getPreviousRolesOfBadEmployee() throws Exception {
//
//        createEmployee();
//
//        mvc.perform(get("/employees/1555/prevroles")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//        ;
//    }
//
//// ----------------------------------------------------------------------------------------------------------------------------------------------
//    // ROLE - DELETE
//
//    @Test
//    public void deleteRole() throws Exception {
//        String uri = "/roles/777";
//
//        createEmployee();
//
//        //assertEquals(roleService.findAllCurrentRoles(1L), listCurrentRoles);
//
//        given(roleService.findRoleById(777L)).willReturn(f1);
//
//        mvc.perform(delete(uri)
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//        ;
//
//        verify(roleService, Mockito.times(1)).deleteRole(f1);
//
//        //assertNotEquals(roleService.findAllCurrentRoles(1L), listCurrentRoles);
//    }
//
//    @Test
//    public void deleteBadRole() throws Exception {
//        String uri = "/roles/7771";
//
//        createEmployee();
//        given(roleService.findRoleById(777L)).willReturn(f1);
//
//        mvc.perform(delete(uri)
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//        ;
//
//        verify(roleService, never()).deleteRole(f1);
//    }
//
//// ----------------------------------------------------------------------------------------------------------------------------------------------
//
//    // HELPMETHODS
//
//    /**
//     * Method to convert objects to JSON's
//     *
//     * @param obj an object is given as a parameter and this object will be
//     *            converted to a JSON
//     * @return
//     * @throws JsonProcessingException
//     */
//    protected String mapToJson(Object obj) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(obj);
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void createEmployee() {
//        given(service.findById(1L)).willReturn(Optional.of(empDummy));
//    }
//
//}
