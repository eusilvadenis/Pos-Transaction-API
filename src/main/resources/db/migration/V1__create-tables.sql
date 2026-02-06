CREATE TABLE devices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    serial_number VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL
);

CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount DECIMAL(19,2) NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    installments INT,
    transaction_status VARCHAR(50),
    created_at DATETIME NOT NULL,
    idempotency_key VARCHAR(255) NOT NULL,
    authorization_code VARCHAR(255),
    device_id BIGINT NOT NULL,

    CONSTRAINT uk_idempotency UNIQUE (idempotency_key),
    CONSTRAINT fk_transaction_device
        FOREIGN KEY (device_id) REFERENCES devices(id)
);
