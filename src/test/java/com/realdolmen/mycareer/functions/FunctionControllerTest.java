package com.realdolmen.mycareer.functions;

import com.realdolmen.mycareer.common.ResourceNotFoundException;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Function;
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
public class FunctionControllerTest {

    @Mock
    private FunctionService serviceMock;

    private FunctionController controller;
    
    private Employee emp;
    private List<Function> functions;
      
    public FunctionControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new FunctionController(serviceMock);
        
        makeEmployee();
        makeFunctions();
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
    
    private void makeFunctions(){
        Function function1 = new Function();
        function1.setTitle("title1");
        function1.setDescription("description1");
        function1.setStart(new Date());
        function1.setId(100L);
        function1.setEmployeeId(1L);
        
        Function function2 = new Function();
        function2.setTitle("title1");
        function2.setDescription("description1");
        function2.setStart(new Date());
        function2.setId(100L);
        function2.setEmployeeId(1L);
        
        functions = new ArrayList<>();
        functions.addAll(Arrays.asList(function1,function2));
    }

    @Test
    public void getFunctionsOfEmployee(){
        controller.getFunctionsOfEmployee(1L);
        
        Mockito.verify(serviceMock).findByEmployeeId(1L);
    }
    
    @Test
    public void updateFunctionsOfEmployee(){
        controller.updateFunctions(1L, functions);
        
        Mockito.verify(serviceMock).deleteByEmployeeId(1L);
        Mockito.verify(serviceMock).saveFunctions(functions);
      
    }
}
