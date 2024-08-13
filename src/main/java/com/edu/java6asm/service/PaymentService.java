package com.edu.java6asm.service;

import com.edu.java6asm.model.Payment;
import com.edu.java6asm.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long paymentId, Double money) {
        var updatedPayment = paymentRepository.findPaymentByOrderId(paymentId);
        updatedPayment.setAmount(money);
        return paymentRepository.save(updatedPayment);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
}
