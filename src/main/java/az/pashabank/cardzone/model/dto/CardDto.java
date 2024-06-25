package az.pashabank.cardzone.model.dto;

import az.pashabank.cardzone.dao.entity.TransactionEntity;

import java.math.BigDecimal;
import java.util.List;

public record CardDto(String pan, Long customerId, BigDecimal balance, List<TransactionEntity> transactionEntities){
    public CardDto(String pan, Long customerId, BigDecimal balance, List<TransactionEntity> transactionEntities) {
        this.pan = pan;
        this.customerId = customerId;
        this.balance = balance;
        this.transactionEntities = transactionEntities;
    }

    public CardDto zeroBalance() {
        return new CardDto(this.pan, this.customerId, BigDecimal.valueOf(0), this.transactionEntities);
    }
}
