package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.NotNull;

public record OrderDTO(

        @NotNull
        Long vehicleId

) {}