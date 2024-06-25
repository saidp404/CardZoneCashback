package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

}