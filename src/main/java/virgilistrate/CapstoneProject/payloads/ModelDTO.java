package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.*;

public record ModelDTO(

        @NotBlank
        String name,

        @NotNull
        Long brandId

) {}