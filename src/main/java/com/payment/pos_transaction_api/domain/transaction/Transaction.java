package com.payment.pos_transaction_api.domain.transaction;

import com.payment.pos_transaction_api.domain.device.Device;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
@Entity(name = "transactions")
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Integer installments;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "idempotency_key", unique = true, nullable = false)
    private String idempotencyKey;

    private String authorizationCode;
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;


    public Transaction(@NotNull BigDecimal amount, @NotNull TransactionType transactionType, Integer installments, Device device) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.installments = installments;
        this.device = device;

    }
}