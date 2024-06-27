package az.pashabank.cardzone.model.dto;

import az.pashabank.cardzone.model.enumfiles.TransactionType;

import java.math.BigDecimal;

public record TransactionDto(TransactionType type, BigDecimal amount, boolean hasCashback) {
}
