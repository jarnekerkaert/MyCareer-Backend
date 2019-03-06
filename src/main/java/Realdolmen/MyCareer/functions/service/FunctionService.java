
package Realdolmen.MyCareer.functions.service;

import Realdolmen.MyCareer.functions.domain.CurrentFunction;
import Realdolmen.MyCareer.functions.domain.Function;
import Realdolmen.MyCareer.functions.domain.PrevFunction;
//import Realdolmen.MyCareer.qualities.domain.StrongQuality;
//import Realdolmen.MyCareer.qualities.domain.WeakQuality;
import java.util.List;

public interface FunctionService<T extends Function> {
    
    /*
    // get
    public List<CurrentFunction> findAllCurrentFunctions();
    public List<PrevFunction> findAllPreviousFunctions();
    
    public List<Function> findAllCurrentFunctionsOfEmployee(Long employeeId);
    public List<Function> findAllPreviousFunctionsOfEmployee(Long employeeId);
    
    // post
    public Function save(CurrentFunction function);
    public Function save(PrevFunction function);
    public List<CurrentFunction> saveListOfCurrentFunctions(List<CurrentFunction> functions);
    public List<PrevFunction> saveListOfPrevFunctions(List<PrevFunction> functions);
    
    */
    // get
        // alle functies in de db
        public List<Function> findAll();
        // alle functies van de meegegeven werknemer
        public List<Function> findByEmployeeId(Long employeeId);
  
        public List<Function> findAllPrevFunctions();
        public List<Function> findAllCurrentFunctions();
        
        public List<Function> findPrevFunctions(Long employeeId);
        public List<Function> findCurrentFunctions(Long employeeId);
        
    // post
    public void saveFunctions(List<Function> functions);
        
    // delete
    public void deletePrevFunction();
    public void deleteCurrentFunction();
    
}
