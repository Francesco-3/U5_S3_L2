package francescodicecca.SpringJWT.repositories;

import francescodicecca.SpringJWT.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    Optional<Trip> findById(UUID id);
}
