package com.edu.java6asm.controller;

import com.edu.java6asm.model.Payment;
import com.edu.java6asm.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        var createdPayment = paymentService.createPayment(payment);
        return ResponseEntity.ok(createdPayment);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updatePayment(@PathVariable Long orderId,
                                           @RequestParam(value = "money") Double money) {
        var createdPayment = paymentService.updatePayment(orderId, money);
        return ResponseEntity.ok(createdPayment);
    }

    @GetMapping
    public ResponseEntity<?> getPayments() {
        var payments = paymentService.getPayments();
        return ResponseEntity.ok(payments);
    }
}
