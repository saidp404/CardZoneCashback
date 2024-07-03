package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.CreationCardDto;
import az.pashabank.cardzone.model.dto.ResponseCardDto;
import az.pashabank.cardzone.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping()
    public Object listCards(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size){
        if (page != null && size != null){
            if (size > 0){
                return cardService.listCards(PageRequest.of(page, size));
            }
        }
        return cardService.findAll();
    }

    @GetMapping("/{cardId}")
    public ResponseCardDto findById(@PathVariable Long cardId) {
        return cardService.findById(cardId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreationCardDto creationCardDto){
        cardService.create(creationCardDto);
    }

    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long cardId) {
        cardService.deleteById(cardId);
    }
}
