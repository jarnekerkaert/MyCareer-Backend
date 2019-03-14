
package com.realdolmen.mycareer.employees;

import com.realdolmen.mycareer.common.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
