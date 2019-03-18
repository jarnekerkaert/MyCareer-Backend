package com.realdolmen.mycareer.ambitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Ambition> findByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Transactional
    public void saveAmbitions(List<Ambition> ambitions) {
        repository.saveAll(ambitions);
    }

    @Transactional
    void deleteByEmployeeId(Long employeeId) {
        repository.deleteByEmployeeId(employeeId);
    }
}
