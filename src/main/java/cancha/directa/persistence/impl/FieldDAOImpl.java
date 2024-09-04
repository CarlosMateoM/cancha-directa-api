package cancha.directa.persistence.impl;

import cancha.directa.model.Field;
import cancha.directa.persistence.IFieldDAO;
import cancha.directa.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FieldDAOImpl implements IFieldDAO {

    private final FieldRepository fieldRepository;

    @Autowired
    public FieldDAOImpl(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Optional<Field> findById(Long id) {
        return this.fieldRepository.findById(id);
    }

    @Override
    public List<Field> findAll() {
        return this.fieldRepository.findAll();
    }

    @Override
    public void save(Field field) {
        this.fieldRepository.save(field);
    }

    @Override
    public void deleteById(Long id) {
        this.fieldRepository.deleteById(id);
    }
}
