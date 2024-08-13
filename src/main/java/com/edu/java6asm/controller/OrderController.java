package com.edu.java6asm.controller;

import com.edu.java6asm.enums.OrderStatus;
import com.edu.java6asm.model.Order;
import com.edu.java6asm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public ResponseEntity<?> getOrders() {
        var orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }
    @PostMapping
    public ResponseEntity<?> createOrders(@RequestBody Order order) {
        order.setStatus(OrderStatus.PENDING);
        var orders = orderService.createOrders(order);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable Long userId) {
        var orders = orderService.getOrderByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancelOrders(@PathVariable Long orderId) {
        var orders = orderService.cancelOrders(orderId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?>updateStatusOrder(@PathVariable Long orderId){
        var orders = orderService.updateStatusOrder(orderId);
        return ResponseEntity.ok(orders);
    }
}
