
package Realdolmen.MyCareer.qualities.repositories;

import Realdolmen.MyCareer.qualities.domain.Quality;
import Realdolmen.MyCareer.qualities.domain.QualityType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityRepository extends JpaRepository<Quality, Long> {
    List<Quality> findByEmployeeId(Long employeeId);
    Quality findQualityById(Long id);
    void deleteByEmployeeId(Long employeeId);
}
