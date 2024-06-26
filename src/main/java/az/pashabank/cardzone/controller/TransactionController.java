package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.TransactionDto;
import az.pashabank.cardzone.model.exception.ExcessiveAmountSent;
import az.pashabank.cardzone.model.exception.NoCardFoundById;
import az.pashabank.cardzone.model.exception.NotEnoughBalance;
import az.pashabank.cardzone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/cards/{cardId}/transactions")
    public void changeCurrentBalance(@RequestBody TransactionDto transactionDto, @PathVariable Long cardId) throws NoCardFoundById {
        transactionService.create(transactionDto, cardId);
    }

    @ExceptionHandler(NoCardFoundById.class)
    public ResponseEntity<?> handleNoCardFoundById(NoCardFoundById exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughBalance.class)
    public ResponseEntity<?> handleNotEnoughBalance(NotEnoughBalance exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExcessiveAmountSent.class)
    public ResponseEntity<?> handleExcessiveAmountSent(ExcessiveAmountSent exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
