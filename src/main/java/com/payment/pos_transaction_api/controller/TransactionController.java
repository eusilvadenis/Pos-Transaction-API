package com.payment.pos_transaction_api.controller;

import com.payment.pos_transaction_api.domain.dto.TransactionRequestDTO;
import com.payment.pos_transaction_api.domain.dto.TransactionResponseDTO;
import com.payment.pos_transaction_api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService service;

    @PostMapping("/{deviceId}")
    @Transactional
    public ResponseEntity<TransactionResponseDTO> createTransaction(@PathVariable Long deviceId, @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey, @Valid @RequestBody TransactionRequestDTO request  ){
        var response = service.createTransaction(request, deviceId, idempotencyKey);
        return ResponseEntity.ok(response);
    }

}
