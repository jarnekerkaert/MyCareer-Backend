
package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.domain.CurrentFunction;
import Realdolmen.MyCareer.domain.Employee;
import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.FunctionListWrapper;
import Realdolmen.MyCareer.domain.PrevFunction;
import Realdolmen.MyCareer.domain.Quality;
import Realdolmen.MyCareer.domain.QualityListWrapper;
import Realdolmen.MyCareer.domain.StrongQuality;
import Realdolmen.MyCareer.domain.WeakQuality;
import Realdolmen.MyCareer.service.EmployeeServiceImpl;
import Realdolmen.MyCareer.service.FunctionService;
import Realdolmen.MyCareer.service.QualityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QualityController.class)
public class QualityControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeServiceImpl service;

    @MockBean
    QualityService qualityService;
    
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
    
    public QualityControllerTest() {
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
    
    // ----------------------------------------------------------------------------------------------------------------------------------------------
    // QUALITY - POST
        
    /**
     * Test for adding a list of strong qualities and a list of weak qualities
     * @throws Exception 
     */
    @Test
    public void postListsOfStrongAndWeakQualities() throws Exception {
        String uri = "/employees/1/qualities";

        //given(service.findEmployeeById(1L)).willReturn(empDummy);
        createEmployee();
        
        mvc.perform(post(uri)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(qualityWrapper))
                )
                .andExpect(status().isOk())
                //.andExpect(content().string(containsString("success")));
               ;
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------
    // QUALITY - DELETE
    
    @Test
    public void deleteWeakQuality() throws Exception {
        String uri = "/weakquality/15";

        createEmployee();        
        given(qualityService.findWeakQualityById(15L)).willReturn(weakquality1);
        
        mvc.perform(delete(uri)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
               ;
        //verify(qualityService, times(1)).deleteWeakQuality(any(WeakQuality.class));
    }
    
    @Test
    public void deleteStrongQuality() throws Exception {
        String uri = "/strongquality/16";

        createEmployee();        
        given(qualityService.findStrongQualityById(16L)).willReturn(strongquality1);
        
        mvc.perform(delete(uri)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
               ;
    }
    
}
