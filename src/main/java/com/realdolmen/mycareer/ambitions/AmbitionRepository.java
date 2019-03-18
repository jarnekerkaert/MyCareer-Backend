package com.realdolmen.mycareer.ambitions;

import com.realdolmen.mycareer.domain.Ambition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface AmbitionRepository extends JpaRepository<Ambition, Long> {

    List<Ambition> findByEmployeeId(Long employeeId);

    Optional<Ambition> findById(Long id);

    void deleteByEmployeeId(Long employeeId);
}
