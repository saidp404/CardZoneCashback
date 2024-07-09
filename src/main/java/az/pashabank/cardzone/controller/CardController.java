package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.CreationCardDto;
import az.pashabank.cardzone.model.dto.ResponseCardDto;
import az.pashabank.cardzone.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @Operation(summary = "Get cards in pages", description = "Returns cards in pages, according to possible filtrations(customerId, minBalance, maxBalance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The cards were not found")
    })
    @GetMapping()
    public Page<ResponseCardDto> listCards(@PageableDefault(size = 5) Pageable pageable,
                                           @RequestParam(required = false) Long customerId,
                                           @RequestParam(required = false, defaultValue = "0") BigDecimal minBalance,
                                           @RequestParam(required = false, defaultValue = "9999999999") BigDecimal maxBalance){
        return cardService.listCards(pageable, customerId, minBalance, maxBalance);
    }

    @Operation(summary = "Get a card by id", description = "Returns a card as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The card was not found")
    })
    @GetMapping("/{cardId}")
    public ResponseCardDto findById(@PathVariable Long cardId) {
        return cardService.findById(cardId);
    }

    @Operation(summary = "Create a card", description = "Creates a card according to the provided pan and customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Such card already exists")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreationCardDto creationCardDto){
        cardService.create(creationCardDto);
    }

    @Operation(summary = "Delete a card by id", description = "Deletes a card as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
    })
    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long cardId) {
        cardService.deleteById(cardId);
    }
}
