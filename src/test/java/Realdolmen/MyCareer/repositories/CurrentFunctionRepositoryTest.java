package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.CurrentFunction;
import Realdolmen.MyCareer.domain.Employee;
import Realdolmen.MyCareer.domain.Function;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class CurrentFunctionRepositoryTest {

    public CurrentFunctionRepositoryTest() {
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CurrentFunctionRepository currentFunctionRepository;
    
    private Employee emp;
    private Function function;
    
    @Before
    public void before(){
        emp = new Employee();
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setCv_filepath("empty");
        emp.setPassword("plaintext");
        
        function = new CurrentFunction();
        function.setDescription("description");
        function.setTitle("title");
        
        // nog aan elkaar linken
        
        entityManager.persist(emp);
        entityManager.persist(function);
        entityManager.flush();
    }

    @Test
    public void findByEmployeeId() {
       // TODO
    }



}
