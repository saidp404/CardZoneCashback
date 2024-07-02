package az.pashabank.cardzone.model.dto;

import az.pashabank.cardzone.model.enumfiles.TransactionType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransactionDto(@NotNull TransactionType type, @NotNull @DecimalMin("0.01") BigDecimal amount, boolean hasCashback) {
}
