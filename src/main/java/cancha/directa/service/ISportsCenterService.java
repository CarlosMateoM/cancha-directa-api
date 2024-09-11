package cancha.directa.service;

import cancha.directa.dto.impl.SportCenterDTO;
import cancha.directa.dto.impl.SportCenterRecord;
import cancha.directa.dto.impl.SportTypeDTO;
import cancha.directa.model.SportsCenter;

import java.util.List;

public interface ISportsCenterService {

    SportCenterRecord findById(Long id);

    List<SportCenterRecord> findAll();

    SportCenterDTO save(SportCenterRecord sportCenterRecord);

    SportCenterDTO updateById(Long id, SportCenterRecord);

    void deleteById(Long id);
}
