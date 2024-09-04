package cancha.directa.dto.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {

    private Long id;
    private String rol;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
}
