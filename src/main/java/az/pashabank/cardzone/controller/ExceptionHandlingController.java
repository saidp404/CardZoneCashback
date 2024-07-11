package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.exception.ExcessiveAmountSentException;
import az.pashabank.cardzone.model.exception.NoCardFoundByIdException;
import az.pashabank.cardzone.model.exception.NotEnoughBalanceException;
import az.pashabank.cardzone.model.exception.NotUniquePanException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
    }

    @ExceptionHandler(NoCardFoundByIdException.class)
    public ResponseEntity<?> handleNoCardFoundByIdException(NoCardFoundByIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<?> handleNotEnoughBalanceException(NotEnoughBalanceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExcessiveAmountSentException.class)
    public ResponseEntity<?> handleExcessiveAmountSentException(ExcessiveAmountSentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotUniquePanException.class)
    public ResponseEntity<?> handleNotUniquePanException(NotUniquePanException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
