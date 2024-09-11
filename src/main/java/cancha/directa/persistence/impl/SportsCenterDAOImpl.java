package cancha.directa.persistence.impl;

import cancha.directa.model.SportsCenter;
import cancha.directa.persistence.ISportsCenterDAO;
import cancha.directa.repository.SportsCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class SportsCenterDAOImpl implements ISportsCenterDAO {

    private final SportsCenterRepository sportsCenterRepository;

    @Autowired
    public SportsCenterDAOImpl(SportsCenterRepository sportsCenterRepository) {
        this.sportsCenterRepository = sportsCenterRepository;
    }

    @Override
    public Optional<SportsCenter> findById(Long id) {
        return this.sportsCenterRepository.findById(id);
    }

    @Override
    public List<SportsCenter> findAll() {
        return this.sportsCenterRepository.findAll();
    }

    @Override
    public SportsCenter save(SportsCenter sportsCenter) {
        return this.sportsCenterRepository.save(sportsCenter);
    }

    @Override
    public void deleteById(Long id) {
        this.sportsCenterRepository.deleteById(id);
    }
}
