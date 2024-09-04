package cancha.directa.dto.impl;

import cancha.directa.dto.IDTO;
import cancha.directa.enums.StatusField;
import cancha.directa.model.Field;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldDTO implements IDTO<FieldDTO, Field> {

    private Long id;
    private String name;
    private String description;
    private StatusField status;
    private Long sportTypeId;
    private Long sportsCenterId;
    private Long scheduleId;

    @Override
    public Field DTOToEntity(FieldDTO dto) {

        Field field = new Field();

        field.setId(dto.getId());
        field.setName(dto.getName());
        field.setDescription(dto.getDescription());
        field.setStatus(dto.getStatus());
        return field;
    }

    @Override
    public FieldDTO EntityToDTO(Field entity) {

        return FieldDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .sportTypeId(entity.getSportType() != null ? entity.getSportType().getId() : null)
                .sportsCenterId(entity.getSportsCenter() != null ? entity.getSportsCenter().getId() : null)
                .scheduleId(entity.getSchedule() != null ? entity.getSchedule().getId() : null)
                .build();
    }

    @Override
    public List<FieldDTO> EntityListToDTOList(List<Field> entityList) {

        return entityList.stream()
                .map(entity -> FieldDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .description(entity.getDescription())
                        .status(entity.getStatus())
                        .sportTypeId(entity.getSportType() != null ? entity.getSportType().getId() : null)
                        .sportsCenterId(entity.getSportsCenter() != null ? entity.getSportsCenter().getId() : null)
                        .scheduleId(entity.getSchedule() != null ? entity.getSchedule().getId() : null)
                        .build()
                ).toList();
    }
}
