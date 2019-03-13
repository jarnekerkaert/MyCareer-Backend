
package com.realdolmen.mycareer.qualities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface QualityRepository extends JpaRepository<Quality, Long> {
    List<Quality> findByEmployeeId(Long employeeId);

    Quality findQualityById(Long id);

    void deleteByEmployeeId(Long employeeId);
}
