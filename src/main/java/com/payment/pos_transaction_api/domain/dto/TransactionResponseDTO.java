package com.payment.pos_transaction_api.domain.dto;

import com.payment.pos_transaction_api.domain.transaction.Transaction;
import com.payment.pos_transaction_api.domain.transaction.TransactionStatus;
import com.payment.pos_transaction_api.domain.transaction.TransactionType;

import java.math.BigDecimal;

public record TransactionResponseDTO(
        Long transactionId,
        TransactionStatus status,
        BigDecimal amount,
        TransactionType transactionType,
        String authorizationCode
) {

    public TransactionResponseDTO(Transaction transaction) {
        this(transaction.getId(), transaction.getTransactionStatus(), transaction.getAmount(), transaction.getTransactionType(), transaction.getAuthorizationCode());
    }
}
