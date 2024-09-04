package cancha.directa.model;

import cancha.directa.enums.Days;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "day_week",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Days dayWeek;

    @Column(
            name = "start_time",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime startTime;

    @Column(
            name = "ent_time",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime endTime;

    @Column(
            columnDefinition = "DECIMAL(10,2)"
    )
    private BigDecimal price;

    @OneToMany(mappedBy = "schedules", targetEntity = ReservationsSchedules.class)
    private List<ReservationsSchedules> reservationsSchedules;

    @OneToMany(mappedBy = "schedule", targetEntity = Field.class)
    private List<Field> field;

}
