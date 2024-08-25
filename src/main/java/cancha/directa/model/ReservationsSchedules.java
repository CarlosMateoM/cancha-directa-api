package cancha.directa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations_schedules")
public class ReservationsSchedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            columnDefinition = "TIMESTAMP",
            nullable = false
    )
    private LocalDate date;

    @ManyToOne(targetEntity = Reservation.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservations;

    @ManyToOne(targetEntity = Schedule.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedules;
}
