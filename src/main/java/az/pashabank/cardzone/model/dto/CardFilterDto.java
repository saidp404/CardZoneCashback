package az.pashabank.cardzone.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardFilterDto {
    private Long customerId;
    private BigDecimal minBalance = BigDecimal.ZERO;
    private BigDecimal maxBalance = BigDecimal.valueOf(999999999);
}
