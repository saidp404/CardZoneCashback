package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.TransactionDto;
import az.pashabank.cardzone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/{cardId}/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void changeCurrentBalance(@RequestBody TransactionDto transactionDto, @PathVariable Long cardId) {
        transactionService.create(transactionDto, cardId);
    }
}
