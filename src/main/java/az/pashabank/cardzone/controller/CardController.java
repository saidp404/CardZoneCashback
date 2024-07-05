package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.CreationCardDto;
import az.pashabank.cardzone.model.dto.ResponseCardDto;
import az.pashabank.cardzone.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping()
    public Page<ResponseCardDto> listCards(@PageableDefault(size = 5) Pageable pageable){
        return cardService.listCards(pageable);
    }

//    @GetMapping()
//    public List<ResponseCardDto> listCards(
//            @RequestParam(required = false) Long customerId,
//            @RequestParam(required = false) BigDecimal minBalance,
//            @RequestParam(required = false) BigDecimal maxBalance){
//        return cardService.findAll();
//    }

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
