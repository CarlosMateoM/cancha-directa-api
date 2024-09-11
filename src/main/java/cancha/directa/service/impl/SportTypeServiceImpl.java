package cancha.directa.service.impl;

import cancha.directa.dto.impl.FieldDTO;
import cancha.directa.dto.impl.SportTypeDTO;
import cancha.directa.exception.RegisterNotFoundException;
import cancha.directa.model.SportType;
import cancha.directa.persistence.ISportTypeDAO;
import cancha.directa.service.ISportTypeService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SportTypeServiceImpl implements ISportTypeService {

    private final ISportTypeDAO sportTypeDAO;

    @Autowired
    public SportTypeServiceImpl(ISportTypeDAO sportTypeDAO){
        this.sportTypeDAO = sportTypeDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public SportTypeDTO findById(Long id) {
         
        SportType sportType = this.sportTypeDAO.findById(id)
                 .orElseThrow(() -> new RuntimeException("Exception: SportType not found"));

        Hibernate.initialize(sportType.getFields());
        SportTypeDTO sportTypeDTO = new SportTypeDTO();

        return sportTypeDTO.toDTOAllAtributes(sportType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SportTypeDTO> findAll() {
        List<SportType> sportTypeList = this.sportTypeDAO.findAll();
        SportTypeDTO sportTypDTO = new SportTypeDTO();

        if(sportTypeList.isEmpty()){
            throw new RuntimeException("Exception: SportType List not found");
        }

        return sportTypDTO.toDTOList(sportTypeList);
    }

    @Override
    public SportTypeDTO save(SportTypeDTO sportTypeDTO) {
        SportTypeDTO spDTO = new SportTypeDTO();
        SportType sportType = spDTO.toEntity(sportTypeDTO);

        SportType newSportType = this.sportTypeDAO.save(sportType);
        return spDTO.toDTO(newSportType);
    }

    @Override
    public SportTypeDTO updateById(Long id, SportTypeDTO updateSportTypeDTO) {

        if(id == null){
            throw new RegisterNotFoundException("ID cannont be null");
        }

        SportType sportTypeExisting = this.sportTypeDAO.findById(id)
                .orElseThrow(() -> new RegisterNotFoundException("Sport type not found with id:" + id));

        if(updateSportTypeDTO.getName() != null){
            sportTypeExisting.setName(updateSportTypeDTO.getName());
        }

        if(updateSportTypeDTO.getFieldDTOS() != null){
            sportTypeExisting.setFields(new FieldDTO().toEntityList(updateSportTypeDTO.getFieldDTOS()));
        }

        this.sportTypeDAO.save(sportTypeExisting);
        return updateSportTypeDTO.toDTO(sportTypeExisting);
    }

    @Override
    public void deleteById(Long id) {
        if(id == null){
            throw new RegisterNotFoundException("ID cannont be null");
        }

        this.sportTypeDAO.findById(id)
                .orElseThrow(() -> new RegisterNotFoundException("Sport type not found with id:" + id));

        this.sportTypeDAO.deleteById(id);
    }
}
