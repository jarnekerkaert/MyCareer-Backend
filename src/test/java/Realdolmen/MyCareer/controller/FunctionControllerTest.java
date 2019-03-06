package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.functions.domain.CurrentFunction;
import Realdolmen.MyCareer.employees.domain.Employee;
import Realdolmen.MyCareer.functions.domain.Function;
import Realdolmen.MyCareer.functions.domain.FunctionListWrapper;
import Realdolmen.MyCareer.functions.domain.PrevFunction;
import Realdolmen.MyCareer.qualities.domain.QualityListWrapper;
import Realdolmen.MyCareer.qualities.domain.StrongQuality;
import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import Realdolmen.MyCareer.employees.service.EmployeeServiceImpl;
import Realdolmen.MyCareer.functions.controller.FunctionController;
import Realdolmen.MyCareer.functions.service.FunctionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.core.Is.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FunctionController.class)
public class FunctionControllerTest {
    
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

    public FunctionControllerTest() {
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
    // FUNCTION - POST
    
    /**
     * Test for adding a current function
     * @throws Exception 
     */
    /*
    @Test
    public void createCurrentFunction() throws Exception {
        String uri = "/employee/postcurrentfunction/1";

        createEmployee();
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
    
    @Test
    public void createCurrentFunctionBadEmployee() throws Exception {
        String uri = "/employee/postcurrentfunction/155";

        createEmployee();
        given(functionService.save(currentfunction)).willReturn(currentfunction);
        
        System.out.println(currentfunction);
        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(currentfunction)))
                //.content(asJsonString(function)))
                .andExpect(status().isNotFound())
                ;
    }*/

    /**
     * Test for adding a previous function
     * @throws Exception 
     */
    /*
    @Test
    // TODO: datums checken !!
    public void createPreviousFunction() throws Exception {
        String uri = "/employee/postpreviousfunction/1";

        createEmployee();
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
    }*/
    /*
    @Test
    // TODO: datums checken !!
    public void createPreviousFunctionBadEmployee() throws Exception {
        String uri = "/employee/postpreviousfunction/551";

        createEmployee();
        given(functionService.save(prevfunction)).willReturn(prevfunction);

        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(prevfunction)))
                //.content(asJsonString(function)))
                .andExpect(status().isNotFound());
    }*/

    /**
     * Test for adding a list of current functions
     * @throws Exception 
     */
    /*
    @Test
    public void postListOfCurrentFunctions() throws Exception {
        String uri = "/employee/postcurrentfunctions/1";

        createEmployee();
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
    }*/
    
    /**
     *  Test for adding a list of previous functions
     * @throws Exception 
     */
    /*
    @Test
    public void postListOfPreviousFunctions() throws Exception {
        String uri = "/employee/postpreviousfunctions/1";

        createEmployee();
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
    }*/
    
    /**
     * Test for adding a list of current functions and a list of previous functions
     * @throws Exception 
     */
    @Test
    public void postFunctions() throws Exception {
        String uri = "/employees/1/functions";

        createEmployee();
        //given(functionService.saveTwoListsOfFunctions(listCurrentfunctions,listPrevfunctions)).willReturn(); 
        
        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                //.content(mapToJson(functionWrapper))
                .content(mapToJson(listFunctions))
                )
                .andExpect(status().isOk())
                //.andExpect(content().string(containsString("success")));
               ;
        
        verify(functionService, Mockito.times(1)).saveFunctions(listFunctions);
        
        given(functionService.findCurrentFunctions(1L)).willReturn(listCurrentFunctions);
        Function curf1 = (Function) functionService.findCurrentFunctions(1L).get(0);
        Function curf2 = (Function) functionService.findCurrentFunctions(1L).get(1);
        
        given(functionService.findPrevFunctions(1L)).willReturn(listPrevFunctions); 
        Function prevf = (Function) functionService.findPrevFunctions(1L).get(0);
        assertEquals(listFunctions.get(0).getTitle(), curf1.getTitle());
        assertEquals(listFunctions.get(1).getTitle(), curf2.getTitle());
        assertEquals(listFunctions.get(2).getTitle(), prevf.getTitle());
    }
    
    @Test
    public void postFunctionsBadEmployee() throws Exception {
        String uri = "/employees/155/functions";

        createEmployee();
        //given(functionService.saveTwoListsOfFunctions(listCurrentfunctions,listPrevfunctions)).willReturn(); 
        
        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(listFunctions))
                //.content(mapToJson(functionWrapper))
                )
                .andExpect(status().isNotFound())
                //.andExpect(content().string(containsString("success")));
               ;
        
        verify(functionService, never()).saveFunctions(listFunctions);
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------

    // FUNCTION - GET
    
    @Test
    public void getCurrentFunctionsOfEmployee() throws Exception {

        createEmployee();
        
        //functionService.saveFunctions(listFunctions);
        given(functionService.findCurrentFunctions(1L)).willReturn(listCurrentFunctions); 

        mvc.perform(get("/employees/1/currentfunctions")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("title1")))
                .andExpect(jsonPath("$[0].description", is("description1")))
                ;
    }
    
    @Test
    public void getCurrentFunctionsOfBadEmployee() throws Exception {

        createEmployee();

        mvc.perform(get("/employees/1555/currentfunctions")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                ;
    }
    
    @Test
    public void getPreviousFunctionsOfEmployee() throws Exception {

        createEmployee();
        
        given(functionService.findPrevFunctions(1L)).willReturn(listPrevFunctions); 

        mvc.perform(get("/employees/1/prevfunctions")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("title3")))
                .andExpect(jsonPath("$[0].description", is("description3")))
                ;
    }
    
    @Test
    public void getPreviousFunctionsOfBadEmployee() throws Exception {

        createEmployee();

        mvc.perform(get("/employees/1555/prevfunctions")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
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
