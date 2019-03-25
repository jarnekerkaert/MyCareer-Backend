//package com.realdolmen.mycareer.controller;
//
//import com.realdolmen.mycareer.domain.Employee;
//import com.realdolmen.mycareer.employees.EmployeeService;
//import com.realdolmen.mycareer.domain.Quality;
//import com.realdolmen.mycareer.quality.QualityController;
//import com.realdolmen.mycareer.quality.QualityService;
//import com.realdolmen.mycareer.domain.QualityType;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
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
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
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
//@WebMvcTest(QualityController.class)
//public class QualityControllerTest {
//    
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private EmployeeService service;
//
//    @MockBean
//    QualityService qualityService;
//    
//    
//    private Employee empDummy;
//    private List<Quality> listQualities;
//    private List<Quality> listStrongQualities;
//    private List<Quality> listWeakQualities;
//    private Quality q1;
//    
//    public QualityControllerTest() {
//    }
//    
//         @Before
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
//        listQualities = new ArrayList<>();
//        listStrongQualities = new ArrayList<>();
//        listWeakQualities = new ArrayList<>();
//        q1 = new Quality();
//        q1.setDescription("java");
//        q1.setType(QualityType.STRONG);
////q1.setType("STRONG");
//        //q1.setEmployee(empDummy);
//        q1.setId(999L);
//        Quality q2 = new Quality();
//        q2.setDescription("unit testing");
//        q2.setType(QualityType.WEAK);
////q2.setType("WEAK");
//        //q2.setEmployee(empDummy);
//        listQualities.add(q1);
//        listQualities.add(q2);
//        listStrongQualities.add(q1);
//        listWeakQualities.add(q2);
//    }
//
//    @Test
//    public void getStrongQualitiesOfEmployee() throws Exception {
//
//        createEmployee();
//        
//        given(qualityService.findAllStrongQualities(1L)).willReturn(listStrongQualities); 
//
//        mvc.perform(get("/employees/1/strongqualities")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].description", is("java")))
//                .andExpect(jsonPath("$[0].type", is("STRONG")))
//                ;
//    }
//    
//    @Test
//    public void getStrongQualitiesOfBadEmployee() throws Exception {
//
//        createEmployee();
//        
//        given(qualityService.findAllStrongQualities(1L)).willReturn(listStrongQualities); 
//
//        mvc.perform(get("/employees/1555/strongqualities")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                ;
//    }
//    
//    @Test
//    public void getWeakQualitiesOfEmployee() throws Exception {
//
//        createEmployee();
//        
//        given(qualityService.findAllWeakQualities(1L)).willReturn(listWeakQualities); 
//
//        mvc.perform(get("/employees/1/weakqualities")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].description", is("unit testing")))
//                .andExpect(jsonPath("$[0].type", is("WEAK")))
//                ;
//    }
//    
//    @Test
//    public void getWeakQualitiesOfBadEmployee() throws Exception {
//
//        createEmployee();
//        
//        given(qualityService.findAllWeakQualities(1L)).willReturn(listWeakQualities); 
//
//        mvc.perform(get("/employees/1555/weakqualities")
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                ;
//    }
//
//    /**
//     * Method to convert objects to JSON's
//     * @param obj an object is given as a parameter and this object will be
//     * converted to a JSON
//     * @return
//     * @throws JsonProcessingException
//     */
//
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
//    private void createEmployee(){
//        given(service.findById(1L)).willReturn(Optional.of(empDummy));
//    }
//
//    @Test
//    public void postQualities() throws Exception {
//        String uri = "/employees/1/quality";
//
//        createEmployee();
//        
//        assertNotEquals(qualityService.findAllStrongQualities(1L), listStrongQualities);
//        assertNotEquals(qualityService.findAllWeakQualities(1L), listWeakQualities);
//        
//        mvc.perform(post(uri)
//                .contentType(APPLICATION_JSON)
//                .content(mapToJson(listQualities))
//                )
//                .andExpect(status().isOk())
//               ;
//        
//        //verify(qualityService, Mockito.times(1)).saveQualities(listQualities);
//        //verify(qualityService, never()).saveQualities(listQualities);
//        
//        given(qualityService.findAllStrongQualities(1L)).willReturn(listStrongQualities);
//        Quality sq = (Quality) qualityService.findAllStrongQualities(1L).get(0);
//        
//        given(qualityService.findAllWeakQualities(1L)).willReturn(listWeakQualities); 
//        Quality wq = (Quality) qualityService.findAllWeakQualities(1L).get(0);
//        assertEquals(listQualities.get(0).getDescription(), sq.getDescription());
//        assertEquals(listQualities.get(1).getDescription(), wq.getDescription());
//        
//        assertEquals(qualityService.findAllStrongQualities(1L), listStrongQualities);
//        assertEquals(qualityService.findAllWeakQualities(1L), listWeakQualities);
//    }
//    
//    @Test
//    public void postQualitiesBadEmployee() throws Exception {
//        String uri = "/employees/155/quality";
//
//        createEmployee();
//        
//        mvc.perform(post(uri)
//                .contentType(APPLICATION_JSON)
//                .content(mapToJson(listQualities))
//                )
//                .andExpect(status().isNotFound())
//               ;
//        
//        verify(qualityService, never()).saveQualities(listQualities);
//    }
//
//    @Test
//    public void deleteQuality() throws Exception {
//        String uri = "/quality/999";
//
//        createEmployee();        
//        
//        //assertEquals(qualityService.findAllStrongQualities(1L), listStrongQualities);
//        
//        given(qualityService.findQualityById(999L).get()).willReturn(q1);
//        
//        mvc.perform(delete(uri)
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//               ;
//        
//        verify(qualityService, Mockito.times(1)).deleteQuality(q1);
//       
//        //assertNotEquals(qualityService.findAllStrongQualities(1L), listStrongQualities);
//    }
//    
//    @Test
//    public void deleteBadQuality() throws Exception {
//        String uri = "/quality/9991";
//
//        createEmployee();        
//        given(qualityService.findQualityById(999L).get()).willReturn(q1);
//        
//        mvc.perform(delete(uri)
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//               ;
//        
//      verify(qualityService, never()).deleteQuality(q1);
//    } 
//}
