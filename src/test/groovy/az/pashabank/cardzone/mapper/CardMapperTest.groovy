package az.pashabank.cardzone.mapper

import az.pashabank.cardzone.dao.entity.CardEntity
import az.pashabank.cardzone.model.dto.ResponseCardDto
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class CardMapperTest extends Specification {
    @Subject
    private CardMapper cardMapper

    void setup(){
        cardMapper = new CardMapperImpl()
    }

    def "cardEntityToResponseCardDto: should return responseCardDto according to the given cardEntity"() {
        given:
        def cardEntity = new CardEntity(
                404L,
                "404",
                404L,
                BigDecimal.valueOf(0),
                LocalDateTime.now(),
                null
        )
        def responseCardDto = new ResponseCardDto(
                "404",
                404L,
                BigDecimal.valueOf(0)
        )

        when:
        def actual = cardMapper.cardEntityToResponseCardDto(cardEntity)

        then:
        actual == responseCardDto
    }

    def "responseCardDtoToCardEntity: should return cardEntity according to the given responseCardDto"() {
        given:
        def cardEntity = new CardEntity(
                null,
                "404",
                404L,
                BigDecimal.valueOf(0),
                null,
                null
        )
        def responseCardDto = new ResponseCardDto(
                "404",
                404L,
                BigDecimal.valueOf(0)
        )

        when:
        def actual = cardMapper.responseCardDtoToCardEntity(responseCardDto)

        then:
        actual == cardEntity
    }

    def "cardEntitiesToResponseCardDtos: should return a list of responseCardDtos according to the given list of cardEntities"() {
        given:
        def cardEntities = [
                new CardEntity(
                null,
                "404",
                404L,
                BigDecimal.valueOf(0),
                null,
                null
                ),
                new CardEntity(
                null,
                "405",
                405L,
                BigDecimal.valueOf(404),
                null,
                null
                )
        ]
        def responseCardDtos = [
                new ResponseCardDto(
                "404",
                404L,
                BigDecimal.valueOf(0)
                ),
                new ResponseCardDto(
                "405",
                405L,
                BigDecimal.valueOf(404)
                )
        ]

        when:
        def actual = cardMapper.cardEntitiesToResponseCardDtos(cardEntities)

        then:
        actual == responseCardDtos
    }
}
