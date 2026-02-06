package com.payment.pos_transaction_api.domain.validations;

import com.payment.pos_transaction_api.domain.dto.TransactionRequestDTO;

public interface TransactionValidator {
     Boolean validate(TransactionRequestDTO request);
    }

