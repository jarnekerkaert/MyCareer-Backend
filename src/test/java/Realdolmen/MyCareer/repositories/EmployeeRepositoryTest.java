
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.Employee;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class EmployeeRepositoryTest {
    
   @Autowired
   private TestEntityManager entityManager;

   @Autowired
   private EmployeeRepository employeeRepository;

   @Test
   public void findEmployeeById() {
       //given
       Employee emp = new Employee();
       emp.setFirstname("Nathan");
       emp.setLastname("Westerlinck");
       emp.setEmail("test@test.com");
       emp.setBirthdate(new Date());
       emp.setCv_filepath("empty");
       emp.setPassword("plaintext");
       entityManager.persist(emp);
       entityManager.flush();
       //employeeRepository.save(emp);

       //when
       Employee testEmp = employeeRepository.findEmployeeById(emp.getId());

       //then
       assertEquals(emp.getId(), testEmp.getId());
       assertEquals(emp.getFirstname(), testEmp.getFirstname());
       // of assertThat(testEmp.getFirstname()).isEqualTo(emp.getFirstname());
   }

}
