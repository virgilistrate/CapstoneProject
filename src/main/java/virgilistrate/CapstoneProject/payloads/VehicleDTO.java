package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.*;
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
        Long brandId,

        @NotNull
        Long modelId,

        @NotNull
        Long bodyTypeId,

        Set<Long> optionalIds

) {}