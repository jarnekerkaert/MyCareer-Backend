package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.employees.Employee;
import Realdolmen.MyCareer.employees.EmployeeController;
import Realdolmen.MyCareer.functions.Function;
import Realdolmen.MyCareer.qualities.Quality;
import Realdolmen.MyCareer.employees.EmployeeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Optional.of;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.verify;
import Realdolmen.MyCareer.functions.FunctionService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeServiceImpl service;

    @MockBean
    FunctionService functionService;

    private Employee empDummy;
    private Function currentfunction;
    private Function prevfunction;
    private List<Function> listCurrentfunctions;
    private List<Function> listPrevfunctions;
    private List<Quality> listStrongQualities;
    private List<Quality> listWeakQualities;
    private Quality weakquality1;
    private Quality strongquality1;
    private List<Function> listFunctions;
    private List<Function> listCurrentFunctions;
    private List<Function> listPrevFunctions;

    public EmployeeControllerTest() {
    }

    @Before
    public void before() {
        // to get employee by id
        empDummy = new Employee();
        empDummy.setFirstname("Nathan");
        empDummy.setLastname("Westerlinck");
        empDummy.setEmail("test@test.com");
        empDummy.setBirthdate(new Date());
        empDummy.setCv_filepath("leeg");
        empDummy.setPassword("plaintext");
        empDummy.setId(1L);
    }
    
// ----------------------------------------------------------------------------------------------------------------------------------------------
    // EMPLOYEE - GET
    
    /**
     * Test for the GET API call getEmployee(Long employeeId) with an existing employeeId
     * @throws Exception 
     */
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
                .andExpect(jsonPath("email", is(empDummy.getEmail())))
                .andExpect(jsonPath("cv_filepath", is(empDummy.getCv_filepath()))) //.andExpect(jsonPath("birthdate", is(empDummy.getBirthdate())))
                ;
    }

    /**
     * Test for the GET API call getEmployee(Long employeeId) with an nonexisting employeeId
     * @throws Exception 
     */
    @Test
    public void employeeNotFound() throws Exception {
        //mvc.perform(get("/employee/25663").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());        
        mvc.perform(get("/employees/25663")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                //.andExpect(content().string("Employee not found with id : '25663'"))
                ;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------

    // HELPMETHODS
    /**
     * Method to convert objects to JSON's
     * @param obj an object is given as a parameter and this object will be
     * converted to a JSON
     * @return
     * @throws JsonProcessingException
     */
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
    
}
