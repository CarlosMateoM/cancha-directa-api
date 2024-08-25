package cancha.directa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            columnDefinition = "varchar(50)",
            nullable = false)
    private String rol;

    @Column(
            name = "first_name",
            columnDefinition = "varchar(100)",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            columnDefinition = "varchar(100)",
            nullable = false
    )
    private String lastName;

    @Column(
            columnDefinition = "varchar(12)",
            nullable = true
    )
    private String phone;

    @Column(
            unique = true,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @OneToMany(mappedBy = "user", targetEntity = Reservation.class)
    private List<Reservation> reservations;

}
