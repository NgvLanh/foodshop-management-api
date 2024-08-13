package com.edu.java6asm.repository;

import com.edu.java6asm.model.Order;
import com.edu.java6asm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByAddressUserId(Long userId);
}
