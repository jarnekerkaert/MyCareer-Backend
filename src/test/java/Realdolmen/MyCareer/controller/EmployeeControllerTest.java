package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.functions.domain.CurrentFunction;
import Realdolmen.MyCareer.employees.domain.Employee;
import Realdolmen.MyCareer.employees.controller.EmployeeController;
import Realdolmen.MyCareer.functions.domain.Function;
import Realdolmen.MyCareer.functions.domain.FunctionListWrapper;
import Realdolmen.MyCareer.functions.domain.PrevFunction;
import Realdolmen.MyCareer.qualities.domain.Quality;
import Realdolmen.MyCareer.qualities.domain.QualityListWrapper;
import Realdolmen.MyCareer.qualities.domain.StrongQuality;
import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import Realdolmen.MyCareer.employees.service.EmployeeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import org.json.JSONObject;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import Realdolmen.MyCareer.functions.service.FunctionService;
import Realdolmen.MyCareer.qualities.service.QualityService;

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
    private CurrentFunction currentfunction;
    private PrevFunction prevfunction;
    private List<CurrentFunction> listCurrentfunctions;
    private List<PrevFunction> listPrevfunctions;
    private FunctionListWrapper functionWrapper;
    private List<StrongQuality> listStrongQualities;
    private List<WeakQuality> listWeakQualities;
    private QualityListWrapper qualityWrapper;
    private WeakQuality weakquality1;
    private StrongQuality strongquality1;
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

        // to post a current function
        currentfunction = new CurrentFunction();
        currentfunction.setTitle("title1");
        currentfunction.setDescription("description1");
        currentfunction.setEmployee(empDummy);

        // to post a previous function
        prevfunction = new PrevFunction();
        prevfunction.setTitle("abc");
        prevfunction.setDescription("def");
        prevfunction.setEmployee(empDummy);

        // TODO uit commentaar zetten en createPreviousFunction testen
        //prevfunction.setStart(new Date());
        //prevfunction.setStart(new Date(2019,8,6));
        //prevfunction.setEnding(new Date());
        
        // to post a list of current functions
        CurrentFunction currentfunction2 = new CurrentFunction();
        currentfunction2.setTitle("title2");
        currentfunction2.setDescription("description2");
        currentfunction2.setEmployee(empDummy);

        listCurrentfunctions = new ArrayList<>();
        listCurrentfunctions.add(currentfunction);
        listCurrentfunctions.add(currentfunction2);
        
        // to post a list of previous functions
        PrevFunction prevfunction2 = new PrevFunction();
        prevfunction2.setTitle("ghi");
        prevfunction2.setDescription("jkl");
        prevfunction2.setEmployee(empDummy);

        listPrevfunctions = new ArrayList<>();
        listPrevfunctions.add(prevfunction);
        listPrevfunctions.add(prevfunction2);
        
        // to post a list of current and previous functions
        functionWrapper = new FunctionListWrapper();
        functionWrapper.setCurrentfunctions(listCurrentfunctions);
        functionWrapper.setPrevfunctions(listPrevfunctions);
        
        // to post a list of strong and weak qualities
        qualityWrapper = new QualityListWrapper();
        listStrongQualities = new ArrayList<>();
        listWeakQualities = new ArrayList<>();
        
        strongquality1 = new StrongQuality();
        strongquality1.setDescription("description strong");
        strongquality1.setId(16L);
        strongquality1.setEmployee(empDummy);
        listStrongQualities.add(strongquality1);
        
        weakquality1 = new WeakQuality();
        weakquality1.setDescription("description weak");
        weakquality1.setId(15L);
        weakquality1.setEmployee(empDummy);
        listWeakQualities.add(weakquality1);
        
        qualityWrapper.setStrongqualities(listStrongQualities);
        qualityWrapper.setWeakqualities(listWeakQualities);
        
        // post list of functions:
        listFunctions = new ArrayList<>();
        listCurrentFunctions = new ArrayList<>();
        listPrevFunctions = new ArrayList<>();
        Function f1 = new Function();
        f1.setTitle("title1");
        f1.setDescription("description1");
        f1.setEmployee(empDummy);
        Function f2 = new Function();
        f2.setTitle("title2");
        f2.setDescription("description2");
        f2.setEmployee(empDummy);
        Function f3 = new Function();
        f3.setTitle("title3");
        f3.setDescription("description3");
        f3.setStart(new Date());
        f3.setEnding(new Date());
        f3.setEmployee(empDummy);
        listFunctions.add(f1);
        listFunctions.add(f2);
        listFunctions.add(f3);
        listCurrentFunctions.add(f1);
        listCurrentFunctions.add(f2);
        listPrevFunctions.add(f3);
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
        given(service.findEmployeeById(1L)).willReturn(empDummy);
    }
    
}
