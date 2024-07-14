package az.pashabank.cardzone.service;

import az.pashabank.cardzone.client.CashbackClient;
import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.dao.entity.TransactionEntity;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final CashbackClient cashbackClient;

    public void create(TransactionDto transactionDto, Long cardId) {
        CardEntity cardEntity = cardRepository.findById(cardId).orElseThrow(() -> new NoCardFoundByIdException("Transaction cannot be made, as the card with id:" + cardId + " does not exist."));

        validateTransaction(transactionDto, cardEntity);

        var transactionEntity = transactionMapper.transactionDtoToTransactionEntity(transactionDto, cardEntity);

        transactionRepository.save(transactionEntity);
        changeCurrentBalance(transactionDto, cardEntity);
    }

    public void getAndApplyCashback() {
        List<TransactionEntity> transactionEntities = transactionRepository.findByHasCashback(true);;
        List<TransactionEntity> newTransactions = new ArrayList<>();

        int from = 0;
        int to = 0;

        while (transactionEntities.size() >= to) {
            to += 50;
            for (TransactionEntity transactionEntity : transactionEntities.subList(from, Math.min(to, transactionEntities.size()))) {
//            BigDecimal cashbackAmount = cashbackClient.getCashback(transactionEntity.getAmount()).getBody().cashbackAmount();
                BigDecimal cashbackAmount = cashbackClient.getCashback(transactionEntity.getAmount());
                System.out.println(cashbackAmount);

                transactionEntity.setHasCashback(false);
                newTransactions.add(transactionEntity);

                TransactionEntity cashbackTransaction = transactionMapper.transactionDtoToTransactionEntity(
                        new TransactionDto(TransactionType.CREDIT, cashbackAmount, false),
                        transactionEntity.getCardEntity()
                );
                newTransactions.add(cashbackTransaction);

                changeCurrentBalance(new TransactionDto(TransactionType.CREDIT, cashbackAmount, false), transactionEntity.getCardEntity());
            }

            transactionRepository.saveAll(newTransactions);
            newTransactions.clear();
            from += 50;
        }
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
