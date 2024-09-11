package cancha.directa.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sports_centers")
@Builder
public class SportsCenter {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(
            nullable = false
    )
    private String address;

    @Column(
            unique = true,
            nullable = false
    )
    private String phone;

    @Column(
            name = "opening_time",
            nullable = false
    )
    private LocalTime openingTime;

    @Column(
            name = "closing_time",
            nullable = false
    )
    private LocalTime closingTime;

    @OneToMany(mappedBy = "sportsCenter", fetch = FetchType.EAGER)
    private List<Field> fields;
}
