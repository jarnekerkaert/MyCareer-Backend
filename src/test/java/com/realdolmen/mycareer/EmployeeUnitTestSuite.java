
package com.realdolmen.mycareer;

import com.realdolmen.mycareer.ambitions.AmbitionControllerTest;
import com.realdolmen.mycareer.controller.AmbitionControllerIntegrationTest;
import com.realdolmen.mycareer.controller.EmployeeControllerIntegrationTest;
import com.realdolmen.mycareer.employees.EmployeeControllerTest;
import com.realdolmen.mycareer.qualities.QualityControllerTest;
import com.realdolmen.mycareer.roles.RoleControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            AmbitionControllerTest.class,
            EmployeeControllerTest.class,
            QualityControllerTest.class,
            RoleControllerTest.class
        }
)
public class EmployeeUnitTestSuite {
    
}
