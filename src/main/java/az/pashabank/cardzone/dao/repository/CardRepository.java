package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity, Long> {

}