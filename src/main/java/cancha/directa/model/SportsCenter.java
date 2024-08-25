package cancha.directa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity (name = "SportCenter")
@Table(name = "sports_centers")
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

    @OneToMany(mappedBy = "sportsCenter", fetch = FetchType.EAGER)
    private List<Field> fields;
}
