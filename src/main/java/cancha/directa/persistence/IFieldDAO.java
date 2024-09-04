package cancha.directa.persistence;

import cancha.directa.model.Field;

import java.util.List;
import java.util.Optional;

public interface IFieldDAO {

    Optional<Field> findById (Long id);

    List<Field> findAll ();

    void save (Field field);

    void deleteById (Long id);
}
