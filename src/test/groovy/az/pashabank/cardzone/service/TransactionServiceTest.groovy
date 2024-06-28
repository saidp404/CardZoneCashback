package az.pashabank.cardzone.service

import az.pashabank.cardzone.dao.repository.CardRepository
import az.pashabank.cardzone.dao.repository.TransactionRepository
import az.pashabank.cardzone.mapper.TransactionMapper
import az.pashabank.cardzone.model.dto.TransactionDto
import az.pashabank.cardzone.model.enumfiles.TransactionType
import az.pashabank.cardzone.model.exception.NoCardFoundByIdException
import spock.lang.Specification
import spock.lang.Subject

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

    def "create: should create a new transaction"() {

    }
}
