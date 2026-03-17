package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.NotBlank;

public record SedeDTO(

        @NotBlank
        String name,

        @NotBlank
        String city,

        @NotBlank
        String adress,

       @NotBlank
       String postalCode,

       @NotBlank
       String phone,

        @NotBlank
        String email

) {}