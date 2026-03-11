package virgilistrate.CapstoneProject.payloads;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

public record ClientDTO(

        @NotNull
        Long userId,

        @NotNull
        String indirizzo,

        @NotNull
        String codiceFiscale





) {}