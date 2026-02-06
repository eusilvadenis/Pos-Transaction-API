package com.payment.pos_transaction_api.domain.validations;

import com.payment.pos_transaction_api.domain.dto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MaxAmountValidator {
    private static final BigDecimal MAX_TRANSACTION_AMOUNT =
            new BigDecimal("2000.00");

    public Boolean validate(TransactionRequestDTO request) {
        return (request.amount().compareTo(MAX_TRANSACTION_AMOUNT) <= 0);

        }
    }