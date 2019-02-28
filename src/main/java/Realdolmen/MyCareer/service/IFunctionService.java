
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.CurrentFunction;
import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.PrevFunction;
import Realdolmen.MyCareer.domain.Subklasse1;
import Realdolmen.MyCareer.domain.Subklasse2;
import java.util.List;

public interface IFunctionService<T extends Function> {
    
    public List<CurrentFunction> findAllCurrentFunctions();
    public List<PrevFunction> findAllPreviousFunctions();
    
    public List<Function> findAllCurrentFunctionsOfEmployee(Long employeeId);
    public List<Function> findAllPreviousFunctionsOfEmployee(Long employeeId);
    
    
    // post
    //public void save(CurrentFunction function);
    public Function save(CurrentFunction function);
    public Function save(PrevFunction function);
    public List<CurrentFunction> saveListOfCurrentFunctions(List<CurrentFunction> functions);
    public List<PrevFunction> saveListOfPrevFunctions(List<PrevFunction> functions);
    public String saveTwoListsOfFunctions(List<CurrentFunction> currentfunctions, List<PrevFunction> prevfunctions);
    
    // get
        // alle functies in de db
        public List<Function> findAll();
        // alle functies van de meegegeven werknemer
        public List<Function> findByEmployee_id(Long employeeId);
    
        public List<Function> findOldFunctions(Long employeeId);
        public List<Function> findCurrentFunctions(Long employeeId);
    
    // delete
    public void deleteOldFunction();
    public void deleteCurrentFunction();
    
    
}
