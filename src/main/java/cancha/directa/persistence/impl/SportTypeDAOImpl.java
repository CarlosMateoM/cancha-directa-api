package cancha.directa.persistence.impl;

import cancha.directa.model.SportType;
import cancha.directa.persistence.ISportTypeDAO;
import cancha.directa.repository.SportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SportTypeDAOImpl implements ISportTypeDAO {

    private final SportTypeRepository sportTypeRepository;

    @Autowired
    public SportTypeDAOImpl (SportTypeRepository sportTypeRepository){
        this.sportTypeRepository = sportTypeRepository;
    }

    @Override
    public Optional<SportType> findById(Long id) {
        return this.sportTypeRepository.findById(id);
    }

    @Override
    public List<SportType> findAll() {
        return this.sportTypeRepository.findAll();
    }

    @Override
    public SportType save(SportType sportType) {
        return this.sportTypeRepository.save(sportType);
    }

    @Override
    public void deleteById(Long id) {
        this.sportTypeRepository.deleteById(id);
    }
}
