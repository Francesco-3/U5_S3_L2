package francescodicecca.SpringJWT.entities;

import francescodicecca.TravelManagement.entities.enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String destination;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TripStatus status = TripStatus.IN_PROGRESS;
}
