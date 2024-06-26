package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.model.dto.CardDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-26T21:04:53+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDto cardEntityToCardDto(CardEntity cardEntity) {
        if ( cardEntity == null ) {
            return null;
        }

        String pan = null;
        Long customerId = null;
        BigDecimal balance = null;

        pan = cardEntity.getPan();
        customerId = cardEntity.getCustomerId();
        balance = cardEntity.getBalance();

        CardDto cardDto = new CardDto( pan, customerId, balance );

        return cardDto;
    }

    @Override
    public CardEntity cardDtoToCardEntity(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        CardEntity cardEntity = new CardEntity();

        cardEntity.setPan( cardDto.pan() );
        cardEntity.setCustomerId( cardDto.customerId() );
        cardEntity.setBalance( cardDto.balance() );

        return cardEntity;
    }

    @Override
    public List<CardDto> cardEntitiesToCardDtos(List<CardEntity> cardEntities) {
        if ( cardEntities == null ) {
            return null;
        }

        List<CardDto> list = new ArrayList<CardDto>( cardEntities.size() );
        for ( CardEntity cardEntity : cardEntities ) {
            list.add( cardEntityToCardDto( cardEntity ) );
        }

        return list;
    }
}
