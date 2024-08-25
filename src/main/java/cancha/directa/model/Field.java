package cancha.directa.model;

import cancha.directa.enums.StatusCourt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "fields")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            columnDefinition = "varchar(60)",
            nullable = false
    )
    private String name;

    @Column(
            columnDefinition = "TEXT",
            nullable = true
    )
    private String description;

    @Column(
            columnDefinition = "varchar(40)",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private StatusCourt status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_type_id")
    private SportType sportType;

    @OneToMany(targetEntity = Reservation.class, mappedBy = "field")
    private List<Reservation> reservations;

    @ManyToOne(targetEntity = SportsCenter.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_center_id", referencedColumnName = "id")
    private SportsCenter sportsCenter;

}
