package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    @Override
    List<TransactionEntity> findAll();

    List<TransactionEntity> findByHasCashback(boolean hasCashback);
}
