package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.CardDto;
import az.pashabank.cardzone.model.exception.NoCardFoundById;
import az.pashabank.cardzone.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/cards")
    public List<CardDto> findAll(){
        return cardService.findAll();
    }

    @GetMapping("/cards/{cardId}")
    public CardDto findById(@PathVariable Long cardId) throws NoCardFoundById {
        return cardService.findById(cardId);
    }

    @PostMapping("/cards")
    public void create(@RequestBody CardDto cardDto){
        cardService.create(cardDto.zeroBalance());
    }

    @DeleteMapping("/cards/{cardId}")
    public void deleteById(@PathVariable Long cardId) throws NoCardFoundById {
        cardService.deleteById(cardId);
    }

    @ExceptionHandler(NoCardFoundById.class)
    public ResponseEntity<?> handleNoCardFoundById(NoCardFoundById exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
