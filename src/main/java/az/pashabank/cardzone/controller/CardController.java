package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.CreationCardDto;
import az.pashabank.cardzone.model.dto.ResponseCardDto;
import az.pashabank.cardzone.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public List<ResponseCardDto> findAll(){
        return cardService.findAll();
    }

    @GetMapping("/{cardId}")
    public ResponseCardDto findById(@PathVariable Long cardId) {
        return cardService.findById(cardId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreationCardDto creationCardDto){
        cardService.create(creationCardDto);
    }

    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long cardId) {
        cardService.deleteById(cardId);
    }
}
