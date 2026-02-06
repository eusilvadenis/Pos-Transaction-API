package com.payment.pos_transaction_api.repositories;

import com.payment.pos_transaction_api.domain.device.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
