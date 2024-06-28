package az.pashabank.cardzone.service

import az.pashabank.cardzone.dao.repository.CardRepository
import az.pashabank.cardzone.dao.repository.TransactionRepository
import az.pashabank.cardzone.mapper.TransactionMapper
import spock.lang.Specification

class TransactionServiceTest extends Specification {
    void setup() {
        def transactionRepository = Mock(TransactionRepository)
        def transactionMapper = Mock(TransactionMapper)
        def cardRepository = Mock(CardRepository)
        def transactionService = new TransactionService(transactionMapper, transactionRepository, cardRepository)
    }

    def "Create"() {
    }
}
