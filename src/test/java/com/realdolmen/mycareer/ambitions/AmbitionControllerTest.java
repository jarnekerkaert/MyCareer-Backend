package com.realdolmen.mycareer.ambitions;

import com.realdolmen.mycareer.domain.Ambition;
import com.realdolmen.mycareer.domain.Employee;
import com.realdolmen.mycareer.domain.Role;
import com.realdolmen.mycareer.domain.Term;
import com.realdolmen.mycareer.roles.RoleService;
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
public class AmbitionControllerTest {

    @Mock
    private AmbitionService serviceMock;

    private AmbitionController controller;

    private Employee emp;
    private List<Ambition> ambitions;

    public AmbitionControllerTest() {
    }

    @Before
    public void setUp() {
        controller = new AmbitionController(serviceMock);

        makeEmployee();
        makeAmbitions();
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

    private void makeAmbitions() {
        Ambition ambition1 = new Ambition();
        ambition1.setTitle("title1");
        ambition1.setMotivation("description1");
        ambition1.setTerm(Term.LONG);
        ambition1.setId(200L);
        ambition1.setEmployeeId(1L);

        Ambition ambition2 = new Ambition();
        ambition2.setTitle("title1");
        ambition2.setMotivation("description1");
        ambition2.setTerm(Term.SHORT);
        ambition2.setId(201L);
        ambition2.setEmployeeId(1L);

        ambitions = new ArrayList<>();
        ambitions.addAll(Arrays.asList(ambition1, ambition2));
    }

    @Test
    public void getAmbitionsOfEmployee() {
        controller.getAmbitionsEmployee(1L);

        Mockito.verify(serviceMock).findByEmployeeId(1L);
    }

    @Test
    public void updateAmbitionsOfEmployee() {
        controller.updateAmbitions(1L, ambitions);

        Mockito.verify(serviceMock).deleteByEmployeeId(1L);
       // Mockito.verify(serviceMock).saveAmbitions(ambitions);

    }

}
