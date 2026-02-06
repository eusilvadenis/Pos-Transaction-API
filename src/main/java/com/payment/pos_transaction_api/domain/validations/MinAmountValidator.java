package com.payment.pos_transaction_api.domain.validations;

import com.payment.pos_transaction_api.domain.dto.TransactionRequestDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MinAmountValidator implements TransactionValidator {
    @Override
    public Boolean validate(TransactionRequestDTO request) {
        if (request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        return true;
    }
}
