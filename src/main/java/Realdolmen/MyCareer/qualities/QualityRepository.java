
package Realdolmen.MyCareer.qualities;

import Realdolmen.MyCareer.qualities.Quality;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface QualityRepository extends JpaRepository<Quality, Long> {
    List<Quality> findByEmployeeId(Long employeeId);
    Quality findQualityById(Long id);
    void deleteByEmployeeId(Long employeeId);
}
