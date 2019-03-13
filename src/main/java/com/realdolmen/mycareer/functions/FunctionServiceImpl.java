
package com.realdolmen.mycareer.functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return repository.findByEmployeeId(employeeId).get().stream().filter( f -> f.getEnding() == null).collect(Collectors.toList());
    }
    
    @Override
    public List<Function> findPrevFunctions(Long employeeId) {
        return repository.findByEmployeeId(employeeId).get().stream().filter( f -> f.getEnding() != null).collect(Collectors.toList());
    }

    @Override
    public Optional<List<Function>> findByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Override
    public Optional<Function> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public void saveFunctions(List<Function> functions) {
        repository.saveAll(functions);
    }

    @Override
    public Function findFunctionById(Long id) {
        return repository.findFunctionById(id);
    }

    @Transactional
    @Override
    public void deleteByEmployeeId(Long employeeId) {
        repository.deleteByEmployeeId(employeeId);
    }

    @Override
    public void deleteFunction(Function function) {
        repository.delete(function);
    }

}
