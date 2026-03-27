package virgilistrate.CapstoneProject.payloads;

import virgilistrate.CapstoneProject.enums.TractionType;

import java.util.List;
import java.util.Set;

public record VehiclePatchDTO(

        String plateNumber,
        Boolean sold,
        Double price,
        Integer yearOfConstruction,
        Integer kilometers,
        String color,
        String fuelType,
        Integer seats,
        Integer doorsNumber,
        Integer engineCapacity,
        Integer enginePower,
        Double engineConsumption,
        TractionType tractiontype,
        Integer vehicleLength,
        Integer vehicleWidth,
        Integer vehicleHeight,
        Integer trunkSize,
        String emissionsClass,
        Integer co2Emissions,
        Long brandId,
        Long modelId,
        Long bodyTypeId,
        Long sedeId,
        Set<Long> optionalIds,
        Set<Long> imageIds,
        List<String> imageUrls

) {}