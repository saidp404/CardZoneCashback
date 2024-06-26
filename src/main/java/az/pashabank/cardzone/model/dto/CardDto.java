package az.pashabank.cardzone.model.dto;

import java.math.BigDecimal;

public record CardDto(String pan, Long customerId, BigDecimal balance){

    public CardDto zeroBalance() {
        return new CardDto(this.pan, this.customerId, BigDecimal.valueOf(0));
    }
}
