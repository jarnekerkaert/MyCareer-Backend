package com.realdolmen.mycareer.PublicEmployee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PublicEmployeeControllerControllerHttpTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void getEmployeeById_() {

    }

    @Test
    public void createEmployee() {
    }

    @Test
    public void updateEmployee() {
    }
}
