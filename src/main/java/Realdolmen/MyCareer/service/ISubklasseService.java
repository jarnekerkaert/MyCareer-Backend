
package Realdolmen.MyCareer.service;

import Realdolmen.MyCareer.domain.Subklasse1;
import Realdolmen.MyCareer.domain.Subklasse2;
import Realdolmen.MyCareer.domain.Superklasse;
import java.util.List;

public interface ISubklasseService<T extends Superklasse> {
     public T findSubklasseById(Long id);
     public List<Subklasse1> findAll1();
      public List<Subklasse2> findAll2();
}
