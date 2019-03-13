
package com.realdolmen.mycareer.functions;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface FunctionRepository extends JpaRepository<Function, Long>{

    List<Function> findByEmployeeId(Long employeeId);



    void deleteByEmployeeId(Long employeeId);
}
