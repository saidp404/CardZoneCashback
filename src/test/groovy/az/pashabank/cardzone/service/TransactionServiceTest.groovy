package az.pashabank.cardzone.service

import az.pashabank.cardzone.dao.entity.CardEntity
import az.pashabank.cardzone.dao.entity.TransactionEntity
import az.pashabank.cardzone.dao.repository.CardRepository
import az.pashabank.cardzone.dao.repository.TransactionRepository
import az.pashabank.cardzone.mapper.TransactionMapper
import az.pashabank.cardzone.model.dto.TransactionDto
import az.pashabank.cardzone.model.enumfiles.TransactionType
import az.pashabank.cardzone.model.exception.ExcessiveAmountSentException
import az.pashabank.cardzone.model.exception.NoCardFoundByIdException
import az.pashabank.cardzone.model.exception.NotEnoughBalanceException
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class TransactionServiceTest extends Specification {

    @Subject
    private TransactionService transactionService
    private TransactionRepository transactionRepository
    private TransactionMapper transactionMapper
    private CardRepository cardRepository

    void setup() {
        transactionRepository = Mock()
        transactionMapper = Mock()
        cardRepository = Mock()
        transactionService = new TransactionService(transactionMapper, transactionRepository, cardRepository)
    }

    def "create: should throw an exception when no card is found by requested id"() {
        given:
        def cardId = 404L
        def transactionDto = new TransactionDto(
                TransactionType.DEBIT,
                BigDecimal.valueOf(404),
                true
        )

        cardRepository.findById(cardId) >> Optional.empty()

        when:
        transactionService.create(transactionDto, cardId)

        then:
        NoCardFoundByIdException exception = thrown()
        exception.getMessage() == "Transaction cannot be made, as the card with id:" + cardId + " does not exist."
    }

    def "create(DEBIT): should create a new transaction"() {
        given:
        def cardId = 404L
        def transactionDto = new TransactionDto(
                TransactionType.DEBIT,
                BigDecimal.valueOf(404),
                true
        )
        def cardEntity = new CardEntity(
                404L,
                "404",
                404L,
                BigDecimal.valueOf(404),
                LocalDateTime.now(),
                null
        )
        def transactionEntity = new TransactionEntity(
                101L,
                TransactionType.DEBIT,
                BigDecimal.valueOf(404),
                cardEntity,
                true,
                LocalDateTime.now()
        )

        when:
        transactionService.create(transactionDto, cardId)

        then:
        1 * cardRepository.findById(cardId) >> Optional.of(cardEntity)
        1 * transactionMapper.transactionDtoToTransactionEntity(transactionDto, cardEntity) >> transactionEntity
        1 * transactionRepository.save(transactionEntity)
        1 * cardRepository.save(cardEntity)

        cardEntity.balance == BigDecimal.ZERO
    }

    def "create(CREDIT): should create a new transaction"() {
        given:
        def cardId = 404L
        def transactionDto = new TransactionDto(
                TransactionType.CREDIT,
                BigDecimal.valueOf(404),
                true
        )
        def cardEntity = new CardEntity(
                404L,
                "404",
                404L,
                BigDecimal.valueOf(404),
                LocalDateTime.now(),
                null
        )
        def transactionEntity = new TransactionEntity(
                101L,
                TransactionType.CREDIT,
                BigDecimal.valueOf(404),
                cardEntity,
                true,
                LocalDateTime.now()
        )

        when:
        transactionService.create(transactionDto, cardId)

        then:
        1 * cardRepository.findById(cardId) >> Optional.of(cardEntity)
        1 * transactionMapper.transactionDtoToTransactionEntity(transactionDto, cardEntity) >> transactionEntity
        1 * transactionRepository.save(transactionEntity)
        1 * cardRepository.save(cardEntity)

        cardEntity.balance == BigDecimal.valueOf(808)
    }

    def "create(DEBIT): should throw an exception when there is not enough balance"() {
        given:
        def cardId = 404L
        def transactionDto = new TransactionDto(
                TransactionType.DEBIT,
                BigDecimal.valueOf(404),
                true
        )
        def cardEntity = new CardEntity(
                404L,
                "404",
                404L,
                BigDecimal.valueOf(0),
                LocalDateTime.now(),
                null
        )

        cardRepository.findById(cardId) >> Optional.of(cardEntity)

        when:
        transactionService.create(transactionDto, cardId)

        then:
        NotEnoughBalanceException exception = thrown()
        exception.getMessage() == "Transaction was unsuccessfull, as there is not enough balance in the card."
    }

    def "create(CREDIT): should throw an exception when excessive amount of money is being sent"() {
        given:
        def cardId = 404L
        def transactionDto = new TransactionDto(
                TransactionType.CREDIT,
                BigDecimal.valueOf(10_000),
                true
        )
        def cardEntity = new CardEntity(
                404L,
                "404",
                404L,
                BigDecimal.valueOf(404),
                LocalDateTime.now(),
                null
        )

        cardRepository.findById(cardId) >> Optional.of(cardEntity)

        when:
        transactionService.create(transactionDto, cardId)

        then:
        ExcessiveAmountSentException exception = thrown()
        exception.getMessage() == "Transaction was unsuccessfull, as the sent amount is too large."
    }
}
