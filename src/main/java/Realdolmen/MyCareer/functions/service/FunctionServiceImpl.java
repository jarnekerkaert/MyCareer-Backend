
package Realdolmen.MyCareer.functions.service;

import Realdolmen.MyCareer.functions.domain.Function;
//import Realdolmen.MyCareer.repositories.CurrentFunctionRepository;
import Realdolmen.MyCareer.functions.repositories.FunctionRepository;
//import Realdolmen.MyCareer.repositories.PrevFunctionRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FunctionServiceImpl implements FunctionService<Function>{
    
    @Autowired 
    private FunctionRepository repository;
    
    @Override
    public List<Function> findAll() {
        return repository.findAll();
    }
    
    @Override
    public List<Function> findAllCurrentFunctions() {
        return repository.findAll().stream().filter( f -> f.getEnding() == null).collect(Collectors.toList());
    }

    @Override
    public List<Function> findAllPrevFunctions() {
        return repository.findAll().stream().filter( f -> f.getEnding() != null).collect(Collectors.toList());
    }
    
    @Override
    public List<Function> findCurrentFunctions(Long employeeId) {
        return repository.findByEmployeeId(employeeId).stream().filter( f -> f.getEnding() == null).collect(Collectors.toList());
    }
    
    @Override
    public List<Function> findPrevFunctions(Long employeeId) {
        return repository.findByEmployeeId(employeeId).stream().filter( f -> f.getEnding() != null).collect(Collectors.toList());
    }

    @Override
    public List<Function> findByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Override
    public void deletePrevFunction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCurrentFunction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public void saveFunctions(List<Function> functions) {
        repository.saveAll(functions);
    }

}
