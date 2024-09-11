package cancha.directa.persistence;

import cancha.directa.model.SportsCenter;

import java.util.List;
import java.util.Optional;

public interface ISportsCenterDAO {

    Optional<SportsCenter> findById(Long id);

    List<SportsCenter> findAll();

    SportsCenter save(SportsCenter sportsCenter);

    void deleteById(Long id);
}
