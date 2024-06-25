package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.dao.entity.TransactionEntity;
import az.pashabank.cardzone.model.dto.TransactionDto;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-25T16:57:39+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDto transactionToTransactionDTO(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }

        BigDecimal amount = null;
        boolean hasCashback = false;

        amount = transactionEntity.getAmount();
        hasCashback = transactionEntity.isHasCashback();

        TransactionDto.Type type = null;

        TransactionDto transactionDto = new TransactionDto( type, amount, hasCashback );

        return transactionDto;
    }

    @Override
    public TransactionEntity transactionDTOToTransaction(TransactionDto transactionDTO, CardEntity cardEntity) {
        if ( transactionDTO == null && cardEntity == null ) {
            return null;
        }

        TransactionEntity transactionEntity = new TransactionEntity();

        if ( transactionDTO != null ) {
            transactionEntity.setAmount( transactionDTO.amount() );
            transactionEntity.setHasCashback( transactionDTO.hasCashback() );
        }
        if ( cardEntity != null ) {
            transactionEntity.setCardEntity( cardEntity );
            transactionEntity.setCreatedAt( cardEntity.getCreatedAt() );
        }

        return transactionEntity;
    }
}
