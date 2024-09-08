package cancha.directa.service;

import cancha.directa.dto.impl.SportTypeDTO;
import cancha.directa.model.SportType;

import java.util.List;
import java.util.Optional;

public interface ISportTypeService {
    SportTypeDTO findById(Long id);

    List<SportTypeDTO> findAll ();

    SportTypeDTO save(SportTypeDTO sportTypeDTO);

    SportTypeDTO updateById (Long id, SportTypeDTO sportTypeDTO);

    void deleteById(Long id);
}
