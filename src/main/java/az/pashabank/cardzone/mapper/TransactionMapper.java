package az.pashabank.cardzone.mapper;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.dao.entity.TransactionEntity;
import az.pashabank.cardzone.model.dto.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto transactionEntityToTransactionDto(TransactionEntity transactionEntity);

    @Mapping(target = "cardEntity", source = "cardEntity")
    @Mapping(target = "id", ignore = true)
    TransactionEntity transactionDtoToTransactionEntity(TransactionDto transactionDTO, CardEntity cardEntity);
}