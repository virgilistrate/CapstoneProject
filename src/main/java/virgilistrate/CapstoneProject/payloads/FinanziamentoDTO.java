package virgilistrate.CapstoneProject.payloads;

import jakarta.validation.constraints.*;

import java.util.Date;

public record FinanziamentoDTO(

        @NotNull
        Long orderId,

        @NotNull
        @Positive
        Double amount,

        @NotNull
        @Positive
        Integer numberOfRates,

        @NotNull
        Date startDate,

        @NotNull
        Date endingDate,

        @NotNull
        @Positive
        Double monthlyRate




) {}