package com.payment.pos_transaction_api.domain.validations;

import com.payment.pos_transaction_api.domain.dto.TransactionRequestDTO;
import com.payment.pos_transaction_api.domain.transaction.TransactionType;
import org.springframework.stereotype.Component;

@Component
public class DebitTransactionInstallmentValidator implements TransactionValidator {
    @Override
    public Boolean validate(TransactionRequestDTO request) {
            if (request.transactionType().equals(TransactionType.DEBIT) && request.installments() > 1) {
                return false;
            }
            return true;
        }
    }
