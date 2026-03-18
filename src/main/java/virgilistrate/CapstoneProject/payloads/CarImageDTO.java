package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.*;

public record CarImageDTO (

    @NotBlank
    String imageUrl,

    @NotNull
    Long vehicleId
) {}

