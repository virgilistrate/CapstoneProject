package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.NotBlank;

public record OptionalDTO(

        @NotBlank
        String name

) {}