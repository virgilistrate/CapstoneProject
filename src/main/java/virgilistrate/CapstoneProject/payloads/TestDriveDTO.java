package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

public record TestDriveDTO(

        @NotNull
        Long clientId,

        @NotNull
        Long vehicleId,

        @NotNull
        LocalDate date,

        @NotNull
        Long sedeId

) {}