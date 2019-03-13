
package com.realdolmen.mycareer.functions;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long>{
    List<Function> findAll();
    Optional<List<Function>> findByEmployeeId(Long employeeId);
    Function findFunctionById(Long id);
    void deleteByEmployeeId(Long employeeId);
}
