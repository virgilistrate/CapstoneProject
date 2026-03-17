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

        @NotBlank
        Integer seats,

        @NotBlank
        Integer doorsNumber,

        @NotBlank
        Integer engineCapacity,

        @NotBlank
        Integer enginePower,

        @NotBlank
        Double engineConsumtion,

        @NotNull
        TractionType tractionType,

        @NotBlank
        Integer vehicleLenght,

        @NotBlank
        Integer vehicleWidth,

        @NotBlank
        Integer vehicleHeight,

        @NotBlank
        Integer trunkSize,

        @NotBlank
        String emissionsClass,

        @NotBlank
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