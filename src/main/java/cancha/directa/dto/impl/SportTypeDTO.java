package cancha.directa.dto.impl;

import cancha.directa.dto.IDTO;
import cancha.directa.model.Field;
import cancha.directa.model.SportType;
import cancha.directa.view.View;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportTypeDTO implements IDTO<SportTypeDTO, SportType> {

    @JsonView(View.general.class)
    private Long id;

    @JsonView(View.general.class)
    @NotBlank
    private String name;
    @JsonView(View.detailed.class)
    @JsonProperty("fields")
    private List<FieldDTO> fieldDTOS;


    @Override
    public SportType toEntity(SportTypeDTO sportTypeDTO) {

        SportType sportType = new SportType();

        sportType.setId(sportTypeDTO.getId());
        sportType.setName(sportTypeDTO.getName());
        return sportType;
    }

    @Override
    public SportTypeDTO toDTO(SportType entity) {

        return SportTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public SportTypeDTO toDTOAllAtributes(SportType entity) {

        return SportTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .fieldDTOS(entity.getFields() != null ? this.toFieldDTOList(entity.getFields()) : null)
                .build();
    }

    @Override
    public List<SportTypeDTO> toDTOList(List<SportType> entityList) {
        return entityList.stream().map(this::toDTO).toList();
    }

    @Override
    public List<SportType> toEntityList(List<SportTypeDTO> DTOList) {
        return DTOList.stream().map(this::toEntity).toList();
    }

    private FieldDTO toFieldDTO(Field field){
        return new FieldDTO().toDTO(field);
    }

    private List<FieldDTO> toFieldDTOList(List<Field> fields){
        return new FieldDTO().toDTOList(fields);
    }
}
