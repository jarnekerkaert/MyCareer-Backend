package Realdolmen.MyCareer.ambitions.service;

import Realdolmen.MyCareer.ambitions.domain.Ambition;

import java.util.List;
import java.util.Optional;

public interface AmbitionService<T extends Ambition> {

    List<Ambition> findAll();

    // all ambitions of the given employee
    List<Ambition> findByEmployeeId(Long employeeId);

//    Optional<Ambition> findByEmployeeId(Long employeeId);

    void saveAmbitions(List<Ambition> ambitions);

    void deleteByEmployeeId(Long employeeId);
}
