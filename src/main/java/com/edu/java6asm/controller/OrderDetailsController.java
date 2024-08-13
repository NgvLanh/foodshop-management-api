package com.edu.java6asm.controller;

import com.edu.java6asm.model.OrderDetails;
import com.edu.java6asm.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-details")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping
    public ResponseEntity<?> createOrderDetails(@RequestBody OrderDetails orderDetails) {
       var createdOrderDetails = orderDetailsService.createOrderDetails(orderDetails);
       return ResponseEntity.ok(createdOrderDetails);
    }
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<?> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        var orderDetails = orderDetailsService.findOrderDetailsByOrderId(orderId);
        return ResponseEntity.ok(orderDetails);
    }
}
