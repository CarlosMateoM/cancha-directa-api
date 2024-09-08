package cancha.directa.persistence;

import cancha.directa.model.SportType;

import java.util.List;
import java.util.Optional;

public interface ISportTypeDAO {

    Optional<SportType> findById(Long id);

    List<SportType> findAll ();

    SportType save(SportType sportType);

    void deleteById(Long id);
}
