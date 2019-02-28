
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.CurrentFunction;
import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.PrevFunction;
import Realdolmen.MyCareer.repositories.CurrentFunctionRepository;
import Realdolmen.MyCareer.repositories.FunctionRepository;
import Realdolmen.MyCareer.repositories.PrevFunctionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionService implements IFunctionService<Function>{

    /*
    @Autowired
    private FunctionRepository repository; */
    
    @Autowired
    private CurrentFunctionRepository currentFunctionRepository;
    
    @Autowired
    private PrevFunctionRepository prevFunctionRepository;
    
    @Override
    public List<Function> findAll() {
        //return repository.findAll();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Function> findByEmployee_id(Long employeeId) {
        //return repository.findByEmployee_id(employeeId);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Function> findOldFunctions(Long employeeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Function> findCurrentFunctions(Long employeeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOldFunction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCurrentFunction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Function save(CurrentFunction function) {
        return currentFunctionRepository.save(function);
    } 
    
    @Override
    public Function save(PrevFunction function) {
        return prevFunctionRepository.save(function);
    }

    @Override
    public List<CurrentFunction> findAllCurrentFunctions() {
        return currentFunctionRepository.findAll();
    }

    @Override
    public List<PrevFunction> findAllPreviousFunctions() {
        return prevFunctionRepository.findAll();
    }

    @Override
    public List<Function> findAllCurrentFunctionsOfEmployee(Long employeeId) {
        return currentFunctionRepository.findByEmployee_id(employeeId);
    }

    @Override
    public List<Function> findAllPreviousFunctionsOfEmployee(Long employeeId) {
        return prevFunctionRepository.findByEmployee_id(employeeId);
    }

    @Override
    public List<CurrentFunction> saveListOfCurrentFunctions(List<CurrentFunction> functions) {
        return currentFunctionRepository.saveAll(functions);
    }

    @Override
    public List<PrevFunction> saveListOfPrevFunctions(List<PrevFunction> functions) {
        return prevFunctionRepository.saveAll(functions);
    }

    @Override
    public String saveTwoListsOfFunctions(List<CurrentFunction> currentfunctions, List<PrevFunction> prevfunctions) {
        currentFunctionRepository.saveAll(currentfunctions);
        prevFunctionRepository.saveAll(prevfunctions);
        return "gelukt";
    }


    
}
