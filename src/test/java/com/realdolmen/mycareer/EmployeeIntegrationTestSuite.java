
package com.realdolmen.mycareer;

import com.realdolmen.mycareer.controller.AmbitionControllerIntegrationTest;
import com.realdolmen.mycareer.controller.EmployeeControllerIntegrationTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            com.realdolmen.mycareer.MyCareerBackendApplicationTests.class,
            com.realdolmen.mycareer.MyCareerBackendApplicationTests.class, 
            com.realdolmen.mycareer.EmployeeUnitTestSuite.class,
            AmbitionControllerIntegrationTest.class,
            EmployeeControllerIntegrationTest.class
        })
public class EmployeeIntegrationTestSuite {

    
}
