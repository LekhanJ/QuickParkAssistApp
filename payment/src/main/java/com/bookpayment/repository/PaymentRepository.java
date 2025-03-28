package com.bookpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookpayment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}