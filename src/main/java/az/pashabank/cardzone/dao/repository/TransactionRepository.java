package az.pashabank.cardzone.dao.repository;

import az.pashabank.cardzone.dao.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
