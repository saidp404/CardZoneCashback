package az.pashabank.cardzone.mapper

import az.pashabank.cardzone.dao.entity.CardEntity
import az.pashabank.cardzone.dao.entity.TransactionEntity
import az.pashabank.cardzone.model.dto.TransactionDto
import az.pashabank.cardzone.model.enumfiles.TransactionType
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class TransactionMapperTest extends Specification {
    @Subject
    private TransactionMapper transactionMapper

    void setup() {
        transactionMapper = new TransactionMapperImpl()
    }

    def "transactionDtoToTransactionEntity: should return a transactionEntity according to the given transactionDto"() {
        given:
        var transactionDto = new TransactionDto(
                TransactionType.DEBIT,
                BigDecimal.valueOf(404),
                true
        )
        var cardEntity = new CardEntity(
                1L,
                "404",
                404L,
                BigDecimal.valueOf(0),
                LocalDateTime.now(),
                null
        )
        var transactionEntity = new TransactionEntity(
                null,
                TransactionType.DEBIT,
                BigDecimal.valueOf(404),
                cardEntity,
                true,
                null
        )

        when:
        var actual = transactionMapper.transactionDtoToTransactionEntity(transactionDto, cardEntity)

        then:
        actual == transactionEntity
    }
}
