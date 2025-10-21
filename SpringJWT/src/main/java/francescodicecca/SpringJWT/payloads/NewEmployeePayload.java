package francescodicecca.SpringJWT.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NewEmployeePayload {
    @NotBlank(message = "Lo username è obbligatorio!")
    @Size(min = 2, max = 30, message = "Deve avere una lunghezza compresa tra 2 e 30 caratteri")
    private String username;
    @NotBlank(message = "Il nome è obbligatorio!")
    @Size(min = 2, max = 30, message = "Il nome deve avere una lunghezza compresa tra 2 e 30 caratteri")
    private String name;
    @NotBlank(message = "Il cognome è obbligatorio!")
    @Size(min = 2, max = 30, message = "Il cognome deve avere una lunghezza compresa tra 2 e 30 caratteri")
    private String surname;
    @NotBlank(message = "L'email è obbligatoria!")
    @Email(message = "L'indirizzo email inserito non è nel formato corretto!")
    private String email;

    private String avatarUrl;
}
