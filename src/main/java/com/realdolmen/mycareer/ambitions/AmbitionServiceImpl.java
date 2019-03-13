package com.realdolmen.mycareer.ambitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AmbitionServiceImpl implements AmbitionService<Ambition> {

    @Autowired
    private AmbitionRepository repository;

    @Override
    public List<Ambition> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Optional<Ambition>> findByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }


    @Transactional
    @Override
    public void saveAmbitions(List<Ambition> ambitions) {
        repository.saveAll(ambitions);
    }

    @Transactional
    @Override
    public void deleteByEmployeeId(Long employeeId) {
        repository.deleteByEmployeeId(employeeId);
    }
}
