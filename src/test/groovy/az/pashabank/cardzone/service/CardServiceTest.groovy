package az.pashabank.cardzone.service

import az.pashabank.cardzone.dao.repository.CardRepository
import az.pashabank.cardzone.mapper.CardMapper
import spock.lang.Specification

class CardServiceTest extends Specification {
    void setup() {
        def cardRepository = Mock(CardRepository)
        def cardMapper = Mock(CardMapper)
        def cardService = new CardService(cardMapper, cardRepository)
    }

    def "FindAll"() {
    }

    def "FindById"() {
    }

    def "Create"() {
    }

    def "DeleteById"() {
    }
}
