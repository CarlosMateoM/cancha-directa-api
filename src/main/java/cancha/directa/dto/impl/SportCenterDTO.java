package cancha.directa.dto.impl;

import cancha.directa.dto.IDTO;
import cancha.directa.model.Field;
import cancha.directa.model.SportsCenter;
import cancha.directa.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportCenterDTO implements IDTO<SportCenterDTO, SportsCenter> {

    @JsonView(View.general.class)
    private Long id;

    @JsonView(View.general.class)
    private String name;

    @JsonView(View.general.class)
    private String address;

    @JsonView(View.general.class)
    private String phone;

    @JsonView(View.general.class)
    private LocalTime openingTime;

    @JsonView(View.general.class)
    private LocalTime closingTime;

    @JsonView(View.detailed.class)
    private List<FieldDTO> fields;

    @Override
    public SportsCenter toEntity(SportCenterDTO dto) {
        return SportsCenter.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .openingTime(dto.getOpeningTime())
                .closingTime(dto.getClosingTime())
                .build();
    }

    @Override
    public SportCenterDTO toDTO(SportsCenter entity) {
        return SportCenterDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .openingTime(entity.getOpeningTime())
                .closingTime(entity.getClosingTime())
                .build();
    }

    @Override
    public SportCenterDTO toDTOAllAtributes(SportsCenter entity) {
        return SportCenterDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .openingTime(entity.getOpeningTime())
                .closingTime(entity.getClosingTime())
                .fields(entity.getFields() != null ? this.toFieldsDTOList(entity.getFields()) : null)
                .build();
    }

    @Override
    public List<SportCenterDTO> toDTOList(List<SportsCenter> entityList) {
        return entityList.stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<SportsCenter> toEntityList(List<SportCenterDTO> DTOList) {
        return DTOList.stream()
                .map(this::toEntity)
                .toList();
    }

    private List<FieldDTO> toFieldsDTOList(List<Field> fields){
        return new FieldDTO().toDTOList(fields);
    }
}
