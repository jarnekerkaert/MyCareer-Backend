
package com.realdolmen.mycareer.roles;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface RoleRepository extends JpaRepository<Role, Long>{

    List<Role> findByEmployeeId(Long employeeId);



    void deleteByEmployeeId(Long employeeId);
}
