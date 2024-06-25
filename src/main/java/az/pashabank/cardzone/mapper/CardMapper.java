package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.model.dto.CardDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardDto cardToCardDTO(CardEntity cardEntity);

    List<CardDto> cardEntitiesToCardDtos(List<CardEntity> cardEntities);

    CardEntity cardDTOToCard(CardDto cardDto);
}
