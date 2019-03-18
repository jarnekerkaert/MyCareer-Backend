
package com.realdolmen.mycareer.functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public
class FunctionService {

    private final FunctionRepository repository;

    @Autowired
    FunctionService(FunctionRepository repository) {
        this.repository = repository;
    }

    List<Function> findAll() {
        return repository.findAll();
    }

    List<Function> findAllCurrentFunctions() {
        return repository.findAll().stream().filter(f -> f.isCurrent()).collect(Collectors.toList());
    }

    List<Function> findAllPrevFunctions() {
        return repository.findAll().stream().filter(f -> !f.isCurrent()).collect(Collectors.toList());
    }

    List<Function> findCurrentFunctions(Long employeeId) {
        return repository.findByEmployeeId(employeeId).stream().filter(f -> f.isCurrent()).collect(Collectors.toList());
    }

    List<Function> findPrevFunctions(Long employeeId) {
        return repository.findByEmployeeId(employeeId).stream().filter(f -> !f.isCurrent()).collect(Collectors.toList());
    }

    List<Function> findByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Transactional
    void saveFunctions(List<Function> functions) {
        repository.saveAll(functions);
    }

    Optional<Function> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    void deleteByEmployeeId(Long employeeId) {
        repository.deleteByEmployeeId(employeeId);
    }

    void deleteFunction(Function function) {
        repository.delete(function);
    }

}
