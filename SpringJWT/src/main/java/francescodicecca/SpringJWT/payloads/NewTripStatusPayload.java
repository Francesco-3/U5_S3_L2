package francescodicecca.SpringJWT.payloads;

import francescodicecca.TravelManagement.entities.enums.TripStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NewTripStatusPayload {
    @NotNull(message = "Lo stato è obbligatorio")
    private TripStatus status;
}
