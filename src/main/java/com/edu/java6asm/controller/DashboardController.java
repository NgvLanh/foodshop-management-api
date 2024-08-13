package com.edu.java6asm.controller;

import com.edu.java6asm.repository.OrderDetailsRepository;
import com.edu.java6asm.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @GetMapping("/count-payments")
    public ResponseEntity<?> getPaymentCount() {
        Long count = paymentRepository.countPaymentByDate(LocalDate.now());
        return ResponseEntity.ok(count);
    }
}
