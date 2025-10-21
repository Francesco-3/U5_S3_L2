package francescodicecca.SpringJWT.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NewTripPayload {
    @NotEmpty(message = "La destinazione è obbligatoria!")
    String destination;
    @NotNull(message = "La data è obbligatoria!")
    LocalDate date;
}
