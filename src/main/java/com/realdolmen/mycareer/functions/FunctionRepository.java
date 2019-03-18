
package com.realdolmen.mycareer.functions;


import com.realdolmen.mycareer.domain.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface FunctionRepository extends JpaRepository<Function, Long>{

    List<Function> findByEmployeeId(Long employeeId);



    void deleteByEmployeeId(Long employeeId);
}
