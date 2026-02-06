package com.payment.pos_transaction_api.domain.validations;

import com.payment.pos_transaction_api.domain.dto.TransactionRequestDTO;
import com.payment.pos_transaction_api.domain.transaction.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class CreditTransactionInstallmentValidator implements TransactionValidator {

    @Override
    public Boolean validate(TransactionRequestDTO request) {
        if (request.transactionType().equals(TransactionType.CREDIT)
                && (request.installments() < 1 || request.installments() > 12)) {
            return false;
        }
        return true;
    }
}

