package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDTO(

        @NotBlank(message = "Il nome è obbligatorio")
        String name,

        @NotBlank(message = "Il cognome è obbligatorio")
        String surname,

        @Email(message = "Email non valida")
        @NotBlank(message = "L'email è obbligatoria")
        String email,

        @NotBlank(message = "La password è obbligatoria")
        @Pattern(
                regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$",
                message = "La password deve contenere almeno una maiuscola, una minuscola, un numero ed essere minimo 8 caratteri"
        )
        String password,

        @NotBlank(message = "Il numero di telefono è obbligatorio")
        String phoneNumber,

        @NotBlank(message = "L'indirizzo è obbligatorio")
        String indirizzo,

        @NotBlank(message = "Il codice fiscale è obbligatorio")
        String codiceFiscale,

        String role
) {}