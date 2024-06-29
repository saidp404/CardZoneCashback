package az.pashabank.cardzone.service

import az.pashabank.cardzone.dao.entity.CardEntity
import az.pashabank.cardzone.dao.entity.TransactionEntity
import az.pashabank.cardzone.dao.repository.CardRepository
import az.pashabank.cardzone.mapper.CardMapper
import az.pashabank.cardzone.model.dto.CreationCardDto
import az.pashabank.cardzone.model.dto.ResponseCardDto
import az.pashabank.cardzone.model.exception.NoCardFoundByIdException
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime


class CardServiceTest extends Specification {
//    private static final EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build()

    @Subject
    private CardService cardService
    private CardMapper cardMapper
    private CardRepository cardRepository

    void setup() {
        cardRepository = Mock()
        cardMapper = Mock()
        cardService = new CardService(cardMapper, cardRepository)
    }

    def "findAll: should return all existing cards"() {
        given:
        def transactionEntity = new TransactionEntity()
        def cards = [
            new CardEntity(
                13L,
                "13",
                13L,
                BigDecimal.valueOf(13),
                LocalDateTime.now(),
                [transactionEntity]
            ),
            new CardEntity(
                14L,
                "14",
                14L,
                BigDecimal.valueOf(14),
                LocalDateTime.now(),
                [transactionEntity])
        ]

        def cardDtos = [
            new ResponseCardDto(
                "13",
                13L,
                BigDecimal.valueOf(13)
                ),
            new ResponseCardDto(
                "14",
                14L,
                BigDecimal.valueOf(14)
                )
        ]

        when:
        def actual = cardService.findAll()

        then:
        1 * cardRepository.findAll() >> cards
        1 * cardMapper.cardEntitiesToResponseCardDtos(cards) >> cardDtos

        actual == cardDtos
    }

    def "findById: should return the card by requested id"() {
        given:
        def cardId = 13L
        def transactionEntity = new TransactionEntity()

        def cardEntity = new CardEntity(
                13L,
                "13",
                13L,
                BigDecimal.valueOf(13),
                LocalDateTime.now(),
                [transactionEntity]
        )

        def responseCardDto = new ResponseCardDto(
                "13",
                13L,
                BigDecimal.valueOf(13)
        )

        when:
        def actual = cardService.findById(cardId)

        then:
        1 * cardRepository.findById(cardId) >> Optional.of(cardEntity)
        1 * cardMapper.cardEntityToResponseCardDto(cardEntity) >> responseCardDto

        actual == responseCardDto
    }

    def "create: should create a new card with 0 balance"() {
        given:
        def creationCardDto = new CreationCardDto(
                "13",
                13L
        )

        def responseCardDto = new ResponseCardDto(
                creationCardDto.pan(),
                creationCardDto.customerId(),
                BigDecimal.ZERO
        )

        def cardEntity = new CardEntity(
                1L,
                responseCardDto.pan(),
                responseCardDto.customerId(),
                responseCardDto.balance(),
                LocalDateTime.now(),
                null
        )

        when:
        cardService.create(creationCardDto)

        then:
        1 * cardMapper.responseCardDtoToCardEntity(responseCardDto) >> cardEntity
        1 * cardRepository.save(cardEntity)
        cardEntity.balance == BigDecimal.ZERO
    }

    def "deleteById: should delete the card by requested id"() {
        given:
        def cardId = 404L

        def transactionEntity = new TransactionEntity()

        def cardEntity = new CardEntity(
                404L,
                "13",
                13L,
                BigDecimal.valueOf(13),
                LocalDateTime.now(),
                [transactionEntity]
        )

        when:
        cardService.deleteById(cardId)

        then:
        1 * cardRepository.findById(cardId) >> Optional.of(cardEntity) // ifPresent?
        1 * cardRepository.deleteById(cardId)
    }

    def "findById: should throw an exception when no card is found by requested id"() {
        given:
        def cardId = 404L

        cardRepository.findById(cardId) >> Optional.empty()

        when:
        cardService.findById(cardId)

        then:
        NoCardFoundByIdException exception = thrown()
        exception.getMessage() == "Card by id:" + cardId + " does not exist."
    }
}
