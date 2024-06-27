package az.pashabank.cardzone.model.dto;

import java.math.BigDecimal;

public record ResponseCardDto(String pan, Long customerId, BigDecimal balance){
}