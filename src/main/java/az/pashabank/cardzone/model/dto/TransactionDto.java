package az.pashabank.cardzone.model.dto;

import java.math.BigDecimal;

public record TransactionDto(Type type, BigDecimal amount, boolean hasCashback) {
    public enum Type {
        DEBIT, CREDIT
    }
}
