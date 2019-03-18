/*
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.CurrentRole;
import Realdolmen.MyCareer.domain.Employee;
import Realdolmen.MyCareer.domain.Role;
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
public class CurrentRoleRepositoryTest {

    public CurrentRoleRepositoryTest() {
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    //@Autowired
    //private CurrentRoleRepository currentRoleRepository;
    
    private Employee emp;
    private Role role;
    
    @Before
    public void before(){
        emp = new Employee();
        emp.setFirstname("Nathan");
        emp.setLastname("Westerlinck");
        emp.setEmail("test@test.com");
        emp.setBirthdate(new Date());
        emp.setCv_filepath("empty");
        emp.setPassword("plaintext");
        
        role = new CurrentRole();
        role.setDescription("description");
        role.setTitle("title");
        
        // nog aan elkaar linken
        
        entityManager.persist(emp);
        entityManager.persist(role);
        entityManager.flush();
    }

    @Test
    public void findByEmployeeId() {
       // TODO
    }



}*/
