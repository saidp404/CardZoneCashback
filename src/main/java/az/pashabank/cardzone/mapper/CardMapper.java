package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.model.dto.CreationCardDto;
import az.pashabank.cardzone.model.dto.ResponseCardDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    ResponseCardDto cardEntityToResponseCardDto(CardEntity cardEntity);

    CardEntity creationCardDtoToCardEntity(CreationCardDto creationCardDto);

    List<ResponseCardDto> cardEntitiesToResponseCardDtos(List<CardEntity> cardEntities);

    default Page<ResponseCardDto> toPageDto(Page<CardEntity> page) {
        return page.map(this::cardEntityToResponseCardDto);
    }
}
