
package com.realdolmen.mycareer.qualities;

import com.realdolmen.mycareer.domain.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface QualityRepository extends JpaRepository<Quality, Long> {
    List<Quality> findByEmployeeId(Long employeeId);

    Optional<Quality> findQualityById(Long id);

    void deleteByEmployeeId(Long employeeId);
}
