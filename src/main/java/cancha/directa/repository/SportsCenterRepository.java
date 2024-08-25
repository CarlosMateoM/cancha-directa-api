package cancha.directa.repository;

import cancha.directa.model.SportsCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsCenterRepository extends JpaRepository<SportsCenter, Long> {
}
