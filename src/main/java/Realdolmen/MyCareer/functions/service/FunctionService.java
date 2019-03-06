package Realdolmen.MyCareer.functions.service;

import Realdolmen.MyCareer.functions.domain.Function;
//import Realdolmen.MyCareer.qualities.domain.StrongQuality;
//import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import java.util.List;

public interface FunctionService<T extends Function> {

    // get
    // all functions in the database
    public List<Function> findAll();
    
    // all functions of the given employee
    public List<Function> findByEmployeeId(Long employeeId);

    public List<Function> findAllPrevFunctions();

    public List<Function> findAllCurrentFunctions();

    public List<Function> findPrevFunctions(Long employeeId);

    public List<Function> findCurrentFunctions(Long employeeId);
    
    public Function findFunctionById(Long id);

    // post
    public void saveFunctions(List<Function> functions);

    // delete
    public void deleteFunction(Function function);

}
