package com.edu.java6asm.repository;

import com.edu.java6asm.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findPaymentByOrderId(Long orderId);
    @Query("SELECT COUNT(p) FROM payments p WHERE p.date = :date")
    Long countPaymentByDate(@Param("date") LocalDate date);
}
