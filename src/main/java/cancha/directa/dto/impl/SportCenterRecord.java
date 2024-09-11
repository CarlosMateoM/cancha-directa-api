package cancha.directa.dto.impl;

import cancha.directa.model.Field;
import cancha.directa.validation.ICreateValidation;
import cancha.directa.validation.IUpdateValidation;
import cancha.directa.view.View;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;
import java.util.List;

//Serializacion (Objeto a JSON)
public record SportCenterRecord(
        @JsonView(View.general.class)
        long id,

        @JsonView(View.general.class)
        String name,

        @JsonView(View.general.class)
        String address,

        @JsonView(View.general.class)
        String phone,

        @JsonView(View.general.class)
        LocalTime openingTime,

        @JsonView(View.general.class)
        LocalTime closingTime,

        @JsonView(View.detailed.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<Field> fields
) {
    @JsonCreator // deserializcion (JSON a Objeto) y para construir a nivel programatico una instancia el record
    public static SportCenterRecord create(
            @Positive(message = "id must be positive")
            @JsonInclude(JsonInclude.Include.NON_NULL)
            long id,

            @NotNull(message = "name can't be null", groups = ICreateValidation.class)
            @NotBlank(message = "the name cannot be empty", groups = {ICreateValidation.class, IUpdateValidation.class})
            @JsonView(View.general.class)
            String name,

            @NotNull(message = "Address can't be null", groups = ICreateValidation.class)
            @NotBlank(message = "the address cannot be empty", groups = {ICreateValidation.class, IUpdateValidation.class})
            @JsonView(View.general.class)
            String address,

            @NotNull(message = "phone can't be null", groups = ICreateValidation.class)
            @NotBlank(message = "the address cannot be empty", groups = {ICreateValidation.class, IUpdateValidation.class})
            @Pattern(regexp = "\\d{10}", message = "phone number must be 10 digits long", groups = {ICreateValidation.class, IUpdateValidation.class})
            @JsonView(View.general.class)
            String phone,

            @NotNull(message = "the opening time cannot be null and void", groups = ICreateValidation.class)
            @JsonView(View.general.class)
            LocalTime openingTime,

            @NotNull(message = "the closing time cannot be null and void", groups = ICreateValidation.class)
            @JsonView
            LocalTime closingTime,

            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonView(View.detailed.class)
            List<Field> fields
    ){
        return new SportCenterRecord(id, name, address, phone, openingTime, closingTime, fields);
    }

}
