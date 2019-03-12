package Realdolmen.MyCareer.controller;

import Realdolmen.MyCareer.ambitions.AmbitionController;
import Realdolmen.MyCareer.ambitions.Ambition;
import Realdolmen.MyCareer.ambitions.Term;
import Realdolmen.MyCareer.ambitions.AmbitionService;
import Realdolmen.MyCareer.employees.Employee;
import Realdolmen.MyCareer.employees.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Optional.of;
import static org.hamcrest.core.Is.is;

import java.util.*;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan(basePackages = "Realdolmen.MyCareer.controller")
@RunWith(SpringRunner.class)
@WebMvcTest(AmbitionController.class)
public class AmbitionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    @MockBean
    AmbitionService ambitionService;

    private Employee empDummy;
    private List<Ambition> ambitions;
    private Ambition a1, a2, a3;

    public AmbitionControllerTest() {
        ambitions = new ArrayList<>();
        a1 = new Ambition();
        a2 = new Ambition();
        a3 = new Ambition();
    }

    @Before
    public void before() {
        // to get employee by id
        empDummy = new Employee();
        empDummy.setFirstname("Jarne");
        empDummy.setLastname("Kerkaert");
        empDummy.setEmail("test@test.com");
        empDummy.setBirthdate(new Date());
        empDummy.setCv_filepath("leeg");
        empDummy.setPassword("plaintext");
        empDummy.setId(1L);

        createEmployee();

        a1.setId(601L);
        a1.setTitle("ik wil springen");
        a1.setMotivation("ik ben een kangoeroe");
        a1.setTerm(Term.SHORT);
        a2.setId(602L);
        a2.setTitle("ik wil zwemmen");
        a2.setMotivation("ik ben een vis blub");
        a1.setTerm(Term.LONG);
        a3.setId(603L);
        a3.setTitle("ik wil vliegen");
        a3.setMotivation("ik ben een voogel");
        a3.setTerm(Term.LONG);

        ambitions.addAll(Arrays.asList(a1, a2, a3));
    }

    @Test
    public void givenAmbitions_whenGetAmbitionsByEmployeeId_thenReturnJsonArray() throws Exception {
        String URI = "/employees/1/ambitions";

        given(ambitionService.findByEmployeeId(1L)).willReturn(ambitions);

        RequestBuilder request = MockMvcRequestBuilders
                .get(URI)
                .contentType(APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is(a1.getTitle())))
                .andExpect(jsonPath("$[0].motivation", is(a1.getMotivation())))
                .andExpect(jsonPath("$[1].title", is(a2.getTitle())))
                .andExpect(jsonPath("$[1].motivation", is(a2.getMotivation())))
                .andExpect(jsonPath("$[2].title", is(a3.getTitle())))
                .andExpect(jsonPath("$[2].motivation", is(a3.getMotivation())));
    }

    @Test
    public void postAmbitions() throws Exception {
        String URI = "/employees/1/ambitions";

        RequestBuilder request = MockMvcRequestBuilders
                .post(URI)
                .contentType(APPLICATION_JSON)
                .content(mapToJson(ambitions));

        mvc.perform(request)
                .andExpect(status().isOk());

        verify(ambitionService, Mockito.times(1)).saveAmbitions(refEq(ambitions));


    }

    /**
     * Method to convert objects to JSON's
     *
     * @param obj an object is given as a parameter and this object will be
     *            converted to a JSON
     * @return
     * @throws JsonProcessingException
     */
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private void createEmployee() {
        given(service.findEmployeeById(1L)).willReturn(empDummy);
        given(service.findById(1L)).willReturn(of(empDummy));
    }

}
