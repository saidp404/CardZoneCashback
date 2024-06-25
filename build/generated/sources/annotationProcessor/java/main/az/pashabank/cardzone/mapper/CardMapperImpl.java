package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.dao.entity.TransactionEntity;
import az.pashabank.cardzone.model.dto.CardDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-25T16:57:39+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDto cardToCardDTO(CardEntity cardEntity) {
        if ( cardEntity == null ) {
            return null;
        }

        String pan = null;
        Long customerId = null;
        BigDecimal balance = null;
        List<TransactionEntity> transactionEntities = null;

        pan = cardEntity.getPan();
        customerId = cardEntity.getCustomerId();
        balance = cardEntity.getBalance();
        List<TransactionEntity> list = cardEntity.getTransactionEntities();
        if ( list != null ) {
            transactionEntities = new ArrayList<TransactionEntity>( list );
        }

        CardDto cardDto = new CardDto( pan, customerId, balance, transactionEntities );

        return cardDto;
    }

    @Override
    public List<CardDto> cardEntitiesToCardDtos(List<CardEntity> cardEntities) {
        if ( cardEntities == null ) {
            return null;
        }

        List<CardDto> list = new ArrayList<CardDto>( cardEntities.size() );
        for ( CardEntity cardEntity : cardEntities ) {
            list.add( cardToCardDTO( cardEntity ) );
        }

        return list;
    }

    @Override
    public CardEntity cardDTOToCard(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        CardEntity cardEntity = new CardEntity();

        cardEntity.setPan( cardDto.pan() );
        cardEntity.setCustomerId( cardDto.customerId() );
        cardEntity.setBalance( cardDto.balance() );
        List<TransactionEntity> list = cardDto.transactionEntities();
        if ( list != null ) {
            cardEntity.setTransactionEntities( new ArrayList<TransactionEntity>( list ) );
        }

        return cardEntity;
    }
}
