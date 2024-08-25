package cancha.directa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "sports_types")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SportType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            columnDefinition = "varchar(60)",
            nullable = false
    )
    private String name;

    @OneToMany(mappedBy = "sportType")
    private List<Field> fields;
}
