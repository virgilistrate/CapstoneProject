package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.NotBlank;

public record BodyTypeDTO(

        @NotBlank
        String name

) {}