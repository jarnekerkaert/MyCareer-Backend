package com.realdolmen.mycareer.employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.realdolmen.mycareer.common.dto.RoleModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static java.util.Optional.of;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    private Employee empDummy;
    private List<RoleModel> roleModels;

    @Before
    public void before() {
        empDummy = makeEmployee();

    }

    @Test
    // TODO check datum
    public void getEmployee() throws Exception {

        createEmployee();

        mvc.perform(get("/employees/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andExpect(content().json("[{'id': 1,'firstname': 'Nathan','lastname': 'Westerlinck}]"));
                // .andExpect(content().string(containsString("Hello World")));
                .andExpect(jsonPath("firstname", is(empDummy.getFirstname())))
                .andExpect(jsonPath("lastname", is(empDummy.getLastname())))
                .andExpect(jsonPath("email", is(empDummy.getEmail())));
    }

    @Test
    public void employeeNotFound() throws Exception {
        //mvc.perform(get("/employee/25663").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());        
        mvc.perform(get("/employees/25663")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
                //.andExpect(content().string("Employee not found with id : '25663'"));
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private void createEmployee(){
        given(service.findById(1L)).willReturn(of(empDummy));
    }

    private Employee makeEmployee() {
        Employee emp = new Employee();
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setPassword("plaintext");
        emp.setId(1L);
        return emp;
    }
}
