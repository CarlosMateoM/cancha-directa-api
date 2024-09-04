package cancha.directa.service.impl;

import cancha.directa.dto.impl.FieldDTO;
import cancha.directa.model.Field;
import cancha.directa.persistence.IFieldDAO;
import cancha.directa.service.IFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceImpl implements IFieldService {

    private final IFieldDAO fieldDAO;

    @Autowired
    public FieldServiceImpl (IFieldDAO fieldDAO){
        this.fieldDAO = fieldDAO;
    }

    @Override
    public Optional<Field> findById(Long id) {
        Optional<Field> fieldOptional = this.fieldDAO.findById(id);

        if(fieldOptional.isPresent()){

            Field field = fieldOptional.get();
            return Optional.of(field);
        }
        return Optional.empty();
    }

    @Override
    public List<Field> findAll() {
        List<Field> fieldList = this.fieldDAO.findAll();

        if(!fieldList.isEmpty()){
            return fieldList;
        }
        return new ArrayList<>();
    }

    @Override
    public void save(Field field) {
        this.fieldDAO.save(field);
    }

    @Override
    public void deleteById(Long id) {
        this.fieldDAO.deleteById(id);
    }
}
