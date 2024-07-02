package az.pashabank.cardzone.model.dto;

import az.pashabank.cardzone.model.validation.CardPanValidity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CreationCardDto(@CardPanValidity @Size(min = 16, max = 16) String pan, @NotNull @Min(10000) @Max(19999) Long customerId){
}