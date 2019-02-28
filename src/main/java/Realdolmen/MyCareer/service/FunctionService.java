
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.repositories.FunctionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionService implements IFunctionService{

     @Autowired
    private FunctionRepository repository;
    
    @Override
    public List<Function> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Function> findByEmployee_id(Long employeeId) {
        return repository.findByEmployee_id(employeeId);
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
    public Function save(Function function) {
        return repository.save(function);
    } 
    
}
