package com.realdolmen.mycareer.functions;

//import Realdolmen.MyCareer.qualities.domain.StrongQuality;
//import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import java.util.List;
import java.util.Optional;

public interface FunctionService<T extends Function> {

    // get
    // all functions in the database
    List<Function> findAll();
    
    // all functions of the given employee
    Optional<List<Function>> findByEmployeeId(Long employeeId);

    Optional<Function> findById(Long id);

    List<Function> findAllPrevFunctions();

    List<Function> findAllCurrentFunctions();

    List<Function> findPrevFunctions(Long employeeId);

    List<Function> findCurrentFunctions(Long employeeId);
    
    Function findFunctionById(Long id);

    // post
    void saveFunctions(List<Function> functions);

    //delete all
    void deleteByEmployeeId(Long employeeId);

    // delete
    void deleteFunction(Function function);

}
