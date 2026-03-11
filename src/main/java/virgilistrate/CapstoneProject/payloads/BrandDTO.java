package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.NotBlank;

public record BrandDTO(

        @NotBlank
        String name

) {}