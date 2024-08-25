package cancha.directa.dto;

import cancha.directa.model.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String rol;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private List<Reservation> reservations;
}
