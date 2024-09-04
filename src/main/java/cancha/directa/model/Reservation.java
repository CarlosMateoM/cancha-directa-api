package cancha.directa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Field.class)
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    private Field field;

    @OneToMany(mappedBy = "reservations", targetEntity = ReservationsSchedules.class)
    private List<ReservationsSchedules> reservationsSchedules;

}
