package az.pashabank.cardzone.specification;

import az.pashabank.cardzone.dao.entity.CardEntity;
import az.pashabank.cardzone.model.dto.CardFilterDto;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CardSpecifications {
    public static Specification<CardEntity> getSpecification(CardFilterDto cardFilterDto) {
        return Specification.where(filterByCustomerId(cardFilterDto.getCustomerId())).and(filterByBalanceRange(cardFilterDto.getMinBalance(), cardFilterDto.getMaxBalance()));
    }

    public static Specification<CardEntity> filterByCustomerId(Long customerId){
        if (customerId == null){
            return null;
        }

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("customerId"), customerId);
    }

    public static Specification<CardEntity> filterByBalanceRange(BigDecimal minBalance, BigDecimal maxBalance){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("balance"), minBalance, maxBalance);
    }
}
