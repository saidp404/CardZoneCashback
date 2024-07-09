package az.pashabank.cardzone.controller;

import az.pashabank.cardzone.model.dto.TransactionDto;
import az.pashabank.cardzone.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "Creates a transaction by cardId", description = "Creates a transaction as per the cardId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Issue with balance or amount sent. Transaction unsuccessful")
    })
    @PostMapping("/{cardId}/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void changeCurrentBalance(@Valid @RequestBody TransactionDto transactionDto, @PathVariable Long cardId) {
        transactionService.create(transactionDto, cardId);
    }
}
