package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.TransactionDto;
import az.pashabank.cardzone.model.exception.ExcessiveAmountSentException;
import az.pashabank.cardzone.model.exception.NoCardFoundByIdException;
import az.pashabank.cardzone.model.exception.NotEnoughBalanceException;
import az.pashabank.cardzone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/{cardId}/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void changeCurrentBalance(@RequestBody TransactionDto transactionDto, @PathVariable Long cardId) throws NoCardFoundByIdException {
        transactionService.create(transactionDto, cardId);
    }

    @ExceptionHandler(NoCardFoundByIdException.class)
    public ResponseEntity<?> handleNoCardFoundById(NoCardFoundByIdException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<?> handleNotEnoughBalance(NotEnoughBalanceException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExcessiveAmountSentException.class)
    public ResponseEntity<?> handleExcessiveAmountSent(ExcessiveAmountSentException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
