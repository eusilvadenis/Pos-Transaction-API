package com.payment.pos_transaction_api.domain.dto;

import com.payment.pos_transaction_api.domain.transaction.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionRequestDTO(

        @NotNull
        BigDecimal amount,

        @NotNull
        TransactionType transactionType,

        @NotNull
        @Min(1)
        Integer installments
) {


}
