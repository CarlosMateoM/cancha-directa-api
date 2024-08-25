package cancha.directa.repository;

import cancha.directa.model.ReservationsSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsSchedulesRepository extends JpaRepository<ReservationsSchedules, Long> {
}
