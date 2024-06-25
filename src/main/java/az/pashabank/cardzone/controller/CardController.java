package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.CardDto;
import az.pashabank.cardzone.service.CardService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/cards/{id}")
    public CardDto findById(@PathVariable Long id){
        return cardService.findById(id);
    }

    @PostMapping("/cards")
    public void create(@RequestBody CardDto cardDto){
        cardService.create(cardDto.zeroBalance());
    }

    @DeleteMapping("/cards/{id}")
    public void deleteById(@PathVariable Long id){
        cardService.deleteById(id);
    }
}
