
package com.realdolmen.mycareer;

import com.realdolmen.mycareer.ambitions.AmbitionControllerTest;
import com.realdolmen.mycareer.ambitions.AmbitionControllerIntegrationTest;
import com.realdolmen.mycareer.employees.EmployeeControllerIntegrationTest;
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
