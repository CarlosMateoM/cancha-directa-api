package cancha.directa.service;

import cancha.directa.model.Field;

import java.util.List;
import java.util.Optional;

public interface IFieldService {
    Optional<Field> findById (Long id);

    List<Field> findAll ();

    void save (Field field);

    void deleteById (Long id);

}
