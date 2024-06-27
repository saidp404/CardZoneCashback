package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.model.dto.ResponseCardDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    ResponseCardDto cardEntityToResponseCardDto(CardEntity cardEntity);

    CardEntity responseCardDtoToCardEntity(ResponseCardDto responseCardDto);

    List<ResponseCardDto> cardEntitiesToResponseCardDtos(List<CardEntity> cardEntities);
}
