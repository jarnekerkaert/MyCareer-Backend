
package com.realdolmen.mycareer.qualities;

import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Function;
import com.realdolmen.mycareer.domain.Quality;
import com.realdolmen.mycareer.domain.QualityType;
import com.realdolmen.mycareer.functions.FunctionController;
import com.realdolmen.mycareer.functions.FunctionService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.*;

@RunWith(MockitoJUnitRunner.class)
public class QualityControllerTest {
    
    @Mock
    private QualityService serviceMock;

    private QualityController controller;
    
    private Employee emp;
    private List<Quality> qualities;
    
    public QualityControllerTest() {
    }
    
     @Before
    public void setUp() {
        controller = new QualityController(serviceMock);
        
        makeEmployee();
        makeQualities();
    }
            

    private void makeEmployee() {
        emp = new Employee();
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setCv_filepath("leeg");
        emp.setPassword("plaintext");
        emp.setId(1L);
    }
    
    private void makeQualities(){
        Quality quality1 = new Quality();
        quality1.setType(QualityType.WEAK);
        quality1.setDescription("description1");
        quality1.setId(300L);
        quality1.setEmployeeId(1L);
        
        Quality quality2 = new Quality();
        quality2.setType(QualityType.STRONG);
        quality2.setDescription("description1");
        quality2.setId(301L);
        quality2.setEmployeeId(1L);
        
        qualities = new ArrayList<>();
        qualities.addAll(Arrays.asList(quality1,quality2));
    }

    @Test
    public void getQualitiesOfEmployee(){
        controller.getAllQualitiesEmployee(1L);
        
        Mockito.verify(serviceMock).findByEmployeeId(1L);
    }
    
    @Test
    public void updateQualitiesOfEmployee(){
        controller.updateQualities(1L, qualities);
        
        Mockito.verify(serviceMock).deleteByEmployeeId(1L);
        Mockito.verify(serviceMock).saveQualities(qualities);
      
    }
}