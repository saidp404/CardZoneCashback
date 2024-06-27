package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.dao.repository.TransactionRepository;
import az.pashabank.cardzone.mapper.TransactionMapper;
import az.pashabank.cardzone.model.dto.TransactionDto;
import az.pashabank.cardzone.model.enumfiles.TransactionType;
import az.pashabank.cardzone.model.exception.ExcessiveAmountSentException;
import az.pashabank.cardzone.model.exception.NoCardFoundByIdException;
import az.pashabank.cardzone.model.exception.NotEnoughBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    public void create(TransactionDto transactionDto, Long cardId) {
        CardEntity cardEntity = cardRepository.findById(cardId).orElseThrow(() -> new NoCardFoundByIdException("Transaction cannot be made, as the card with id:" + cardId + " does not exist."));

        validateTransaction(transactionDto, cardEntity);

        var transaction = transactionMapper.transactionDtoToTransactionEntity(transactionDto, cardEntity);

        transactionRepository.save(transaction);
        changeCurrentBalance(transactionDto, cardEntity);
    }

    private void changeCurrentBalance(TransactionDto transactionDto, CardEntity cardEntity){
        if (transactionDto.type().equals(TransactionType.DEBIT)){
            cardEntity.setBalance(cardEntity.getBalance().subtract(transactionDto.amount()));
        }
        else {
            cardEntity.setBalance(cardEntity.getBalance().add(transactionDto.amount()));
        }
        cardRepository.save(cardEntity);
    }

    private void validateTransaction(TransactionDto transactionDto, CardEntity cardEntity){
        if (transactionDto.type().equals(TransactionType.DEBIT) && transactionDto.amount().compareTo(cardEntity.getBalance()) > 0){
            throw new NotEnoughBalanceException("Transaction was unsuccessfull, as there is not enough balance in the card.");
        }

        if (transactionDto.type().equals(TransactionType.CREDIT) && transactionDto.amount().compareTo(BigDecimal.valueOf(10_000)) >= 0){
            throw new ExcessiveAmountSentException("Transaction was unsuccessfull, as the sent amount is too large.");
        }
    }
}
