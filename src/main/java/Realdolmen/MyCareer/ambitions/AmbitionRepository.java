package Realdolmen.MyCareer.ambitions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AmbitionRepository extends JpaRepository<Ambition, Long> {

    List<Optional<Ambition>> findByEmployeeId(Long employeeId);

//    Optional<Ambition> findByEmployeeId(Long employeeId);

    void deleteByEmployeeId(Long employeeId);
}
