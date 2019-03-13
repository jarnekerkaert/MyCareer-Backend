package com.realdolmen.mycareer.ambitions;

import java.util.List;
import java.util.Optional;

public interface AmbitionService<T extends Ambition> {

    List<Ambition> findAll();

    List<Optional<Ambition>> findByEmployeeId(Long employeeId);

    void saveAmbitions(List<Ambition> ambitions);

    void deleteByEmployeeId(Long employeeId);
}
