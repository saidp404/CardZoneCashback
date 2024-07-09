package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CardRepository extends PagingAndSortingRepository<CardEntity, Long>, JpaSpecificationExecutor<CardEntity> {
    @Override
    List<CardEntity> findAll();

//    List<CardEntity> findByCustomerId(Long customerId);

    boolean existsByPan(String pan);

    boolean existsByCustomerId(Long customerId);
}