package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.domain.CurrentFunction;
import Realdolmen.MyCareer.domain.Employee;
import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.FunctionListWrapper;
import Realdolmen.MyCareer.domain.PrevFunction;
import Realdolmen.MyCareer.domain.QualityListWrapper;
import Realdolmen.MyCareer.domain.StrongQuality;
import Realdolmen.MyCareer.domain.WeakQuality;
import Realdolmen.MyCareer.service.EmployeeService;
import Realdolmen.MyCareer.service.IFunctionService;
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
import Realdolmen.MyCareer.service.IQualityService;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    @MockBean
    IFunctionService functionService;

    @MockBean
    IQualityService qualityService;

    private Employee empDummy;
    private CurrentFunction currentfunction;
    private PrevFunction prevfunction;
    private List<CurrentFunction> listCurrentfunctions;
    private List<PrevFunction> listPrevfunctions;
    private FunctionListWrapper functionWrapper;
    private List<StrongQuality> listStrongQualities;
    private List<WeakQuality> listWeakQualities;
    private QualityListWrapper qualityWrapper;

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
        empDummy.setFunctions(new ArrayList<>());
        empDummy.setQualities(new ArrayList<>());

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
        
        StrongQuality strongquality1 = new StrongQuality();
        strongquality1.setDescription("description strong");
        strongquality1.setEmployee(empDummy);
        listStrongQualities.add(strongquality1);
        
        WeakQuality weakquality1 = new WeakQuality();
        weakquality1.setDescription("description weak");
        weakquality1.setEmployee(empDummy);
        listWeakQualities.add(weakquality1);
        
        qualityWrapper.setStrongqualities(listStrongQualities);
        qualityWrapper.setWeakqualities(listWeakQualities);
        
    }
    
// ----------------------------------------------------------------------------------------------------------------------------------------------
    // EMPLOYEE - GET
    
    /**
     * Test for the GET API call getEmployee(Long employee_id) with an existing employee_id
     * @throws Exception 
     */
    @Test
    // TODO check datum
    public void givenEmployee_whenGetEmployee_thenReturnJson() throws Exception {

        given(service.findEmployeeById(1L)).willReturn(empDummy);

        mvc.perform(get("/employee/1")
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
     * Test for the GET API call getEmployee(Long employee_id) with an nonexisting employee_id
     * @throws Exception 
     */
    @Test
    public void employeeNotFound() throws Exception {
        //mvc.perform(get("/employee/25663").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound());        
        mvc.perform(get("/employee/25663")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee not found with id : '25663'"));
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------    
    // FUNCTION - POST
    
    /**
     * Test for adding a current function
     * @throws Exception 
     */
    @Test
    public void createCurrentFunction() throws Exception {
        String uri = "/employee/postcurrentfunction/1";

        given(functionService.save(currentfunction)).willReturn(currentfunction);
        System.out.println(currentfunction);
        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(currentfunction)))
                //.content(asJsonString(function)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description", is(currentfunction.getDescription())))
                .andExpect(jsonPath("title", is(currentfunction.getTitle())));
    }

    /**
     * Test for adding a previous function
     * @throws Exception 
     */
    @Test
    // TODO: datums checken !!
    public void createPreviousFunction() throws Exception {
        String uri = "/employee/postpreviousfunction";

        given(functionService.save(prevfunction)).willReturn(prevfunction);

        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(prevfunction)))
                //.content(asJsonString(function)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description", is(prevfunction.getDescription())))
                .andExpect(jsonPath("start", is(prevfunction.getStart())))
                .andExpect(jsonPath("ending", is(prevfunction.getEnding())))
                .andExpect(jsonPath("title", is(prevfunction.getTitle())));
    }

    /**
     * Test for adding a list of current functions
     * @throws Exception 
     */
    @Test
    public void postListOfCurrentFunctions() throws Exception {
        String uri = "/employee/postcurrentfunctions";

        given(functionService.saveListOfCurrentFunctions(listCurrentfunctions)).willReturn(listCurrentfunctions); // returnt eigenlijk een array van current functions

        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(listCurrentfunctions)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("title1")))
                .andExpect(jsonPath("$[0].description", is("description1")))
                //.andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].title", is("title2")))
                .andExpect(jsonPath("$[1].description", is("description2")))
                //.andExpect(jsonPath("$[1].id", is(2)))
               ;
    }
    
    /**
     *  Test for adding a list of previous functions
     * @throws Exception 
     */
    @Test
    public void postListOfPreviousFunctions() throws Exception {
        String uri = "/employee/postpreviousfunctions";

        given(functionService.saveListOfPrevFunctions(listPrevfunctions)).willReturn(listPrevfunctions); // returnt eigenlijk een array van previous functions

        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(listPrevfunctions)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("abc")))
                .andExpect(jsonPath("$[0].description", is("def")))
                //.andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].title", is("ghi")))
                .andExpect(jsonPath("$[1].description", is("jkl")))
                //.andExpect(jsonPath("$[1].id", is(2)))
               ;
    }
    
    /**
     * Test for adding a list of current functions and a list of previous functions
     * @throws Exception 
     */
    @Test
    public void postListsOfCurrentAndPreviousFunctions() throws Exception {
        String uri = "/employee/functions";

        given(functionService.saveTwoListsOfFunctions(listCurrentfunctions,listPrevfunctions)).willReturn("success"); 
        
        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(functionWrapper))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("success")));
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
    
// ----------------------------------------------------------------------------------------------------------------------------------------------
    // QUALITY - POST
    
        
    /**
     * Test for adding a list of strong qualities and a list of weak qualities
     * @throws Exception 
     */
    @Test
    public void postListsOfStrongAndWeakQualities() throws Exception {
        String uri = "/employee/qualities/1";

        given(qualityService.saveTwoListsOfQualities(listStrongQualities,listWeakQualities)).willReturn("success"); 
        
        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(qualityWrapper))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("success")));
               ;
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------
    // QUALITY - DELETE
    
}
