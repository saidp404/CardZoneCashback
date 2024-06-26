package az.pashabank.cardzone.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public enum Type {
        DEBIT, CREDIT;
    };
    private Type type;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonBackReference
    private CardEntity cardEntity;

    @Column(name = "has_cashback")
    private boolean hasCashback;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
