
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.Function;
import java.util.List;

public interface IFunctionService {
    // post
    
    
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
