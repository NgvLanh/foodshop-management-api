package com.edu.java6asm.service;

import com.edu.java6asm.enums.OrderStatus;
import com.edu.java6asm.model.Order;
import com.edu.java6asm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByUserId(Long userId){
        return orderRepository.findOrderByAddressUserId(userId);
    }

    public Order createOrders(Order order) {
        return orderRepository.save(order);
    }

    public Order cancelOrders(Long orderId) {
        var order = orderRepository.findById(orderId).orElse(null);
        if (order.getStatus() != OrderStatus.PENDING)
            return null;
        order.setStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
    }

    public Order updateStatusOrder(Long orderId) {
        var order = orderRepository.findById(orderId).orElse(null);
        switch (order.getStatus()) {
            case PENDING -> order.setStatus(OrderStatus.CONFIRMED);
            case CONFIRMED -> order.setStatus(OrderStatus.SHIPPED);
            case SHIPPED -> order.setStatus(OrderStatus.DELIVERED);
            case CANCELED -> {
                return null;
            }
        }
        return orderRepository.save(order);
    }
}
