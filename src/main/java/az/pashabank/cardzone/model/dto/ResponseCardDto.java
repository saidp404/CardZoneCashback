package az.pashabank.cardzone.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ResponseCardDto(@Schema(example = "1234567891012131", required = true) String pan, @Schema(example = "12345", required = true) Long customerId, @Schema(example = "404.40", required = true) BigDecimal balance){
}