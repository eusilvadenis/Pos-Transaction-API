package com.payment.pos_transaction_api.service;

import com.payment.pos_transaction_api.domain.device.Device;
import com.payment.pos_transaction_api.domain.dto.TransactionRequestDTO;
import com.payment.pos_transaction_api.domain.dto.TransactionResponseDTO;
import com.payment.pos_transaction_api.domain.transaction.Transaction;
import com.payment.pos_transaction_api.domain.transaction.TransactionStatus;
import com.payment.pos_transaction_api.domain.validations.TransactionValidator;
import com.payment.pos_transaction_api.repositories.DeviceRepository;
import com.payment.pos_transaction_api.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    List<TransactionValidator> validator;


    public TransactionResponseDTO createTransaction(TransactionRequestDTO request, Long deviceId, String idempotencyKey) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Terminal not found"));
        if (!device.getActive()) {
            throw new RuntimeException("Inactive device");
        }

        String key = idempotencyKey != null ? idempotencyKey : UUID.randomUUID().toString();
        var existing = transactionRepository.findByIdempotencyKey(key);
        if (existing.isPresent()) {
            Transaction existingTransaction = existing.get();

            return new TransactionResponseDTO(existingTransaction);
        }

        var transaction = new Transaction(request.amount(), request.transactionType(), request.installments(), device);
        transaction.setIdempotencyKey(key);
        transaction.setCreatedAt(OffsetDateTime.now());
        boolean approved = validator.stream().allMatch(v -> v.validate(request));
        if (approved) {

            transaction.setAuthorizationCode("AUTH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            transaction.setTransactionStatus(TransactionStatus.AUTHORIZED);
        } else {
            transaction.setTransactionStatus(TransactionStatus.DENIED);
            transaction.setAuthorizationCode(null);
        }

        transactionRepository.save(transaction);
        return new TransactionResponseDTO(transaction);
    }
}
