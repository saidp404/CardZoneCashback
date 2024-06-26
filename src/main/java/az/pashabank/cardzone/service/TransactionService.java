package az.pashabank.cardzone.service;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.dao.repository.CardRepository;
import az.pashabank.cardzone.dao.repository.TransactionRepository;
import az.pashabank.cardzone.mapper.TransactionMapper;
import az.pashabank.cardzone.model.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    public void create(TransactionDto transactionDto, Long cardId){
        CardEntity cardEntity = cardRepository.findById(cardId).orElseThrow();
        var transaction = transactionMapper.transactionDtoToTransactionEntity(transactionDto, cardEntity);
        transaction.setCreatedAt(LocalDateTime.now());

        transactionRepository.save(transaction);
        changeCurrentBalance(transactionDto, cardEntity);
    }

    public void changeCurrentBalance(TransactionDto transactionDto, CardEntity cardEntity){
        if (transactionDto.type().equals(TransactionDto.Type.DEBIT)){
            cardEntity.setBalance(cardEntity.getBalance().add(transactionDto.amount()));
        }
        else {
            cardEntity.setBalance(cardEntity.getBalance().subtract(transactionDto.amount()));
        }
        cardRepository.save(cardEntity);
    }
}
