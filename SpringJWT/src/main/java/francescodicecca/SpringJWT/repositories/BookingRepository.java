package francescodicecca.SpringJWT.repositories;

import francescodicecca.TravelManagement.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByEmployeeIdAndRequestDate(UUID employeeId, LocalDate requestDate);
}
