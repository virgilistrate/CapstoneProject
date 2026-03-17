package virgilistrate.CapstoneProject.payloads;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import virgilistrate.CapstoneProject.enums.TractionType;

import java.util.Set;

public record VehicleDTO(

        @NotBlank
        String plateNumber,

        @NotNull
        @Positive
        Double price,

        @NotNull
        Integer yearOfConstruction,

        @NotNull
        Integer kilometers,

        @NotBlank
        String color,

        @NotBlank
        String fuelType,

        @NotNull
        Integer seats,

        @NotNull
        Integer doorsNumber,

        @NotNull
        Integer engineCapacity,

        @NotNull
        Integer enginePower,

        @NotNull
        Double engineConsumption,

        @NotNull
        TractionType tractiontype,

        @NotNull
        Integer vehicleLength,

        @NotNull
        Integer vehicleWidth,

        @NotNull
        Integer vehicleHeight,

        @NotNull
        Integer trunkSize,

        @NotBlank
        String emissionsClass,

        @NotNull
        Integer co2Emissions,

        @NotNull
        Long brandId,

        @NotNull
        Long modelId,

        @NotNull
        Long bodyTypeId,

        @NotNull
        Long sedeId,


        Set<Long> optionalIds,

         Set<Long> imageIds,

        Set<Long> maintenanceIds






) {}