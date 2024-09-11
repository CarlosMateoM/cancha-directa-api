package cancha.directa.service.impl;

import cancha.directa.dto.impl.SportCenterDTO;
import cancha.directa.dto.impl.SportCenterRecord;
import cancha.directa.exception.IdNullException;
import cancha.directa.exception.RegisterNotFoundException;
import cancha.directa.model.SportsCenter;
import cancha.directa.persistence.ISportsCenterDAO;
import cancha.directa.service.ISportsCenterService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SportsCenterServiceImpl implements ISportsCenterService {

    private final ISportsCenterDAO sportsCenterDAO;

    @Autowired
    public SportsCenterServiceImpl(ISportsCenterDAO sportsCenterDAO){
        this.sportsCenterDAO = sportsCenterDAO;
    }


    @Override
    public SportCenterRecord findById(Long id) {

        if(id == null){
            throw new IdNullException("Id is null");
        }

        SportsCenter sportsCenter = this.sportsCenterDAO.findById(id)
                .orElseThrow(() -> new RegisterNotFoundException("SportsCenter not found"));

        return ;
    }

    @Override
    public List<SportCenterRecord> findAll() {
        return List.of();
    }

    @Override
    public SportCenterDTO save(SportCenterRecord sportCenterRecord) {
        return null;
    }

    @Override
    public SportCenterDTO updateById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

}
