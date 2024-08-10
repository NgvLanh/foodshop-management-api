package com.edu.java6asm.repository;

import com.edu.java6asm.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long id);
}
