package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.dao.entity.TransactionEntity;
import az.pashabank.cardzone.model.dto.TransactionDto;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-26T21:04:53+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDto transactionEntityToTransactionDto(TransactionEntity transactionEntity) {
        if ( transactionEntity == null ) {
            return null;
        }

        TransactionDto.Type type = null;
        BigDecimal amount = null;
        boolean hasCashback = false;

        type = typeToType( transactionEntity.getType() );
        amount = transactionEntity.getAmount();
        hasCashback = transactionEntity.isHasCashback();

        TransactionDto transactionDto = new TransactionDto( type, amount, hasCashback );

        return transactionDto;
    }

    @Override
    public TransactionEntity transactionDtoToTransactionEntity(TransactionDto transactionDTO, CardEntity cardEntity) {
        if ( transactionDTO == null && cardEntity == null ) {
            return null;
        }

        TransactionEntity transactionEntity = new TransactionEntity();

        if ( transactionDTO != null ) {
            transactionEntity.setType( typeToType1( transactionDTO.type() ) );
            transactionEntity.setAmount( transactionDTO.amount() );
            transactionEntity.setHasCashback( transactionDTO.hasCashback() );
        }
        if ( cardEntity != null ) {
            transactionEntity.setCardEntity( cardEntity );
            transactionEntity.setCreatedAt( cardEntity.getCreatedAt() );
        }

        return transactionEntity;
    }

    protected TransactionDto.Type typeToType(TransactionEntity.Type type) {
        if ( type == null ) {
            return null;
        }

        TransactionDto.Type type1;

        switch ( type ) {
            case DEBIT: type1 = TransactionDto.Type.DEBIT;
            break;
            case CREDIT: type1 = TransactionDto.Type.CREDIT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + type );
        }

        return type1;
    }

    protected TransactionEntity.Type typeToType1(TransactionDto.Type type) {
        if ( type == null ) {
            return null;
        }

        TransactionEntity.Type type1;

        switch ( type ) {
            case DEBIT: type1 = TransactionEntity.Type.DEBIT;
            break;
            case CREDIT: type1 = TransactionEntity.Type.CREDIT;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + type );
        }

        return type1;
    }
}
