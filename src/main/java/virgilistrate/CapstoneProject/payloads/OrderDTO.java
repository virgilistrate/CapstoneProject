package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.NotNull;

public record OrderDTO(

        @NotNull
        Long clientId,

        @NotNull
        Long vehicleId,

        @NotNull
        Long consulenteId

) {}