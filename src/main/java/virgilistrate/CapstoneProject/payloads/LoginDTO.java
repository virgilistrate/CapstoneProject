package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginDTO(

        @NotBlank(message = "Il campo email è obbligatorio")
        @Email(message = "Formato email non valido")
        String email,

        @NotBlank(message = "Il campo password è obbligatorio")
        @Pattern(
                regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$",
                message = "La password deve contenere almeno una maiuscola, una minuscola, un numero ed essere di almeno 8 caratteri"
        )
        String password
) {}