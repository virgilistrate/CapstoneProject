package virgilistrate.CapstoneProject.payloads;

import virgilistrate.CapstoneProject.enums.MaintenanceType;

import java.time.LocalDate;

public record MaintenancePatchDTO(

        MaintenanceType type,
        LocalDate date,
        Long km,
        Long vehicleId

) {}