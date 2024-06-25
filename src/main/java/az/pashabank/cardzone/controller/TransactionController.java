package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.TransactionDto;
import az.pashabank.cardzone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/cards/{cardId}/transactions")
    public void changeCurrentBalance(@RequestBody TransactionDto transactionDto, @PathVariable Long cardId){
        transactionService.create(transactionDto, cardId);
    }
}
