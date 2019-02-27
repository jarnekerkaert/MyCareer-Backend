
package Realdolmen.MyCareer.repositories;

import Realdolmen.MyCareer.domain.Werknemer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long>{
    Werknemer findWerknemerById(Long userId);
}
