package az.pashabank.cardzone.dao.entity;

import az.pashabank.cardzone.model.enumfiles.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TransactionType type;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonBackReference
    private CardEntity cardEntity;

    @Column(name = "has_cashback")
    private boolean hasCashback;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
