package com.realdolmen.mycareer.Ambitions;

import com.realdolmen.mycareer.common.Term;
import com.realdolmen.mycareer.common.dto.AmbitionModel;
import com.realdolmen.mycareer.common.dto.EmployeeModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AmbitionControllerTest {

    @Mock
    private AmbitionService serviceMock;
    private AmbitionController controller;
    private List<Ambition> ambitions;
    private List<AmbitionModel> ambitionModels;

    @Before
    public void setUp() {
        controller = new AmbitionController(serviceMock);

        ambitions = makeAmbitions();
        ambitionModels = makeAmbitionModels();
    }

    @Test
    public void getAmbitionsOfEmployee() {
        controller.getAmbitionsEmployee(1L);

        Mockito.verify(serviceMock).findByEmployeeId(1L);
    }

    @Test
    public void updateAmbitionsOfEmployee() {
        controller.updateAmbitions(1L, ambitionModels);

        Mockito.verify(serviceMock).deleteByEmployeeId(1L);
        Mockito.verify(serviceMock).saveAmbitions(ArgumentMatchers.refEq(ambitions));
    }

    private List<Ambition> makeAmbitions() {
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

        return new ArrayList<>(Arrays.asList(ambition1, ambition2));
    }

    private List<AmbitionModel> makeAmbitionModels() {
        AmbitionModel ambition1 = new AmbitionModel();
        ambition1.setTitle("title1");
        ambition1.setMotivation("description1");
        ambition1.setTerm(Term.LONG);
        ambition1.setId(200L);
        ambition1.setEmployeeId(1L);

        AmbitionModel ambition2 = new AmbitionModel();
        ambition2.setTitle("title1");
        ambition2.setMotivation("description1");
        ambition2.setTerm(Term.SHORT);
        ambition2.setId(201L);
        ambition2.setEmployeeId(1L);

        return new ArrayList<>(Arrays.asList(ambition1, ambition2));
    }
}
