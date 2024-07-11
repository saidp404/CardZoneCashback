package az.pashabank.cardzone.model.dto;

import az.pashabank.cardzone.model.validator.CardPanValidity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CreationCardDto(@Schema(example = "1234567891012131", required = true) @CardPanValidity() @Size(min = 16, max = 16, message = "Invalid pan size: Pan must contain 16 digit") String pan,
                              @Schema(example = "12345", required = true) @NotNull(message = "Invalid customer id: Customer id can't be null") @Min(10000) @Max(19999) Long customerId){
}