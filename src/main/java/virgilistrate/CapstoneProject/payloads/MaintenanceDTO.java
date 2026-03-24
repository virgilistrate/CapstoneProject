package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import virgilistrate.CapstoneProject.enums.MaintenanceType;

import java.time.LocalDate;

public record MaintenanceDTO(

        @NotNull
        MaintenanceType type,

        @NotNull
        LocalDate date,

        @NotNull
        @PositiveOrZero
        Long km,

        @NotNull
        Long vehicleId

) {}