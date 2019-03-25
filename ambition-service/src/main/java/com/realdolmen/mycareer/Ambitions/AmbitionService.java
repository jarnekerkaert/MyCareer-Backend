package com.realdolmen.mycareer.Ambitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public
class AmbitionService {

    private final AmbitionRepository repository;

    @Autowired
    AmbitionService(AmbitionRepository repository) {
        this.repository = repository;
    }

    List<Ambition> findAll() {
        return repository.findAll();
    }

    List<Ambition> findByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Transactional
    void saveAmbitions(List<Ambition> ambitions) {
        repository.saveAll(ambitions);
    }

    @Transactional
    void deleteByEmployeeId(Long employeeId) {
        repository.deleteByEmployeeId(employeeId);
    }
}
