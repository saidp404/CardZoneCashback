package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.CardEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CardRepository extends PagingAndSortingRepository<CardEntity, Long> {
    @Override
    List<CardEntity> findAll();

    boolean existsByPan(String pan);
}