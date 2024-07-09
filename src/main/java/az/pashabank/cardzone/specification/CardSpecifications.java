package az.pashabank.cardzone.specification;

import az.pashabank.cardzone.dao.entity.CardEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CardSpecifications {
    public static Specification<CardEntity> filterByCustomerId(Long customerId){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("customerId"), customerId);
    }

    public static Specification<CardEntity> filterByBalanceRange(BigDecimal minBalance, BigDecimal maxBalance){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("balance"), minBalance, maxBalance);
    }
}
