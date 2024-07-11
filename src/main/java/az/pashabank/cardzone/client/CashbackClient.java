package az.pashabank.cardzone.client;

import az.pashabank.cardzone.model.dto.CashbackResponseDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class CashbackClient extends RestTemplate {
    private final RestTemplate restTemplate;
//    private final String baseUrl = "https://cardzone-cashback-api-c2f5b8105e2b.herokuapp.com";

    public CashbackClient(@Lazy RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    public ResponseEntity<CashbackResponseDto> getCashback(BigDecimal transactionAmount) {
//         return restTemplate.getForEntity(baseUrl + "/api/cashback?transactionAmount=" + transactionAmount, CashbackResponseDto.class);
//    }

    public BigDecimal getCashback(BigDecimal transactionAmount) {
        return transactionAmount.multiply(BigDecimal.valueOf(0.02));
    }
}
