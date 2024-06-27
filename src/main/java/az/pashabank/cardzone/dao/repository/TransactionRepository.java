package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

}
