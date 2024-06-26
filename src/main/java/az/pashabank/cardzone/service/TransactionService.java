package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.dao.repository.TransactionRepository;
import az.pashabank.cardzone.mapper.TransactionMapper;
import az.pashabank.cardzone.model.dto.TransactionDto;
import az.pashabank.cardzone.model.exception.ExcessiveAmountSent;
import az.pashabank.cardzone.model.exception.NoCardFoundById;
import az.pashabank.cardzone.model.exception.NotEnoughBalance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    public void create(TransactionDto transactionDto, Long cardId) throws NoCardFoundById {
        CardEntity cardEntity = cardRepository.findById(cardId).orElseThrow(() -> new NoCardFoundById("Transaction cannot be made, as the card with id:" + cardId + " does not exist."));

        if (transactionDto.type().equals(TransactionDto.Type.DEBIT) && transactionDto.amount().compareTo(cardEntity.getBalance()) > 0){
            throw new NotEnoughBalance("Transaction was unsuccessfull, as there is not enough balance in the card.");
        }

        if (transactionDto.type().equals(TransactionDto.Type.CREDIT) && transactionDto.amount().compareTo(BigDecimal.valueOf(10_000)) >= 0){
            throw new ExcessiveAmountSent("Transaction was unsuccessfull, as the sent amount is too large.");
        }

        var transaction = transactionMapper.transactionDtoToTransactionEntity(transactionDto, cardEntity);
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);
        changeCurrentBalance(transactionDto, cardEntity);
    }

    public void changeCurrentBalance(TransactionDto transactionDto, CardEntity cardEntity){
        if (transactionDto.type().equals(TransactionDto.Type.DEBIT)){
            cardEntity.setBalance(cardEntity.getBalance().subtract(transactionDto.amount()));
        }
        else {
            cardEntity.setBalance(cardEntity.getBalance().add(transactionDto.amount()));
        }
        cardRepository.save(cardEntity);
    }
}
