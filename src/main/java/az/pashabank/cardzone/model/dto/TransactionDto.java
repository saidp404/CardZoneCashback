package az.pashabank.cardzone.model.dto;

import az.pashabank.cardzone.model.enumfiles.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransactionDto(@Schema(example = "DEBIT", required = true) @NotNull TransactionType type,
                             @Schema(example = "4040", required = true) @NotNull @DecimalMin("0.01") BigDecimal amount,
                             @Schema(example = "false") boolean hasCashback) {
}
