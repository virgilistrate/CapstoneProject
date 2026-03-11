package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.*;

public record UserDTO(

        @NotBlank
        String name,

        @NotBlank
        String surname,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        String phoneNumber,

        @NotBlank
        String role

) {}