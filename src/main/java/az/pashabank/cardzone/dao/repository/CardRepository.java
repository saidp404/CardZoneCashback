package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.CardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<CardEntity, Long> {
    @Override
    List<CardEntity> findAll();

    boolean existsByPan(String pan);
}