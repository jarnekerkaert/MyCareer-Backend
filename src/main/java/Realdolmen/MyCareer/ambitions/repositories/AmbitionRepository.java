package Realdolmen.MyCareer.ambitions.repositories;

import Realdolmen.MyCareer.ambitions.domain.Ambition;
import Realdolmen.MyCareer.functions.domain.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AmbitionRepository extends JpaRepository<Ambition, Long> {

    List<Ambition> findByEmployeeId(Long employeeId);

//    Optional<Ambition> findByEmployeeId(Long employeeId);

    void deleteByEmployeeId(Long employeeId);
}
