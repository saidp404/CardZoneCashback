package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.CardDto;
import az.pashabank.cardzone.model.exception.NoCardFoundByIdException;
import az.pashabank.cardzone.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public List<CardDto> findAll(){
        return cardService.findAll();
    }

    @GetMapping("/{cardId}")
    public CardDto findById(@PathVariable Long cardId) throws NoCardFoundByIdException {
        return cardService.findById(cardId);
    }

    @PostMapping
    public void create(@RequestBody CardDto cardDto){
        cardService.create(cardDto.zeroBalance());
    }

    @DeleteMapping("/{cardId}")
    public void deleteById(@PathVariable Long cardId) throws NoCardFoundByIdException {
        cardService.deleteById(cardId);
    }

    @ExceptionHandler(NoCardFoundByIdException.class)
    public ResponseEntity<?> handleNoCardFoundById(NoCardFoundByIdException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
