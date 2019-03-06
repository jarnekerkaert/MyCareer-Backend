
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.CurrentFunction;
import Realdolmen.MyCareer.domain.Function;
import Realdolmen.MyCareer.domain.PrevFunction;
//import Realdolmen.MyCareer.repositories.CurrentFunctionRepository;
import Realdolmen.MyCareer.repositories.FunctionRepository;
import java.util.ArrayList;
//import Realdolmen.MyCareer.repositories.PrevFunctionRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FunctionServiceImpl implements FunctionService<Function>{

    /*
    @Autowired
    private FunctionRepository repository; */
    
//    @Autowired
//    private CurrentFunctionRepository currentFunctionRepository;
//    
//    @Autowired
//    private PrevFunctionRepository prevFunctionRepository;
    
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
    
    
    /*
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
        return currentFunctionRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Function> findAllPreviousFunctionsOfEmployee(Long employeeId) {
        return prevFunctionRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<CurrentFunction> saveListOfCurrentFunctions(List<CurrentFunction> functions) {
        return currentFunctionRepository.saveAll(functions);
    }

    @Override
    public List<PrevFunction> saveListOfPrevFunctions(List<PrevFunction> functions) {
        return prevFunctionRepository.saveAll(functions);
    }

    @Transactional
    @Override
    public void saveTwoListsOfFunctions(List<CurrentFunction> currentfunctions, List<PrevFunction> prevfunctions) {
        currentFunctionRepository.saveAll(currentfunctions);
        prevFunctionRepository.saveAll(prevfunctions);
    }*/

    @Transactional
    @Override
    public void saveFunctions(List<Function> functions) {
        repository.saveAll(functions);
    }








}
