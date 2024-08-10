package com.edu.java6asm.repository;

import com.edu.java6asm.model.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {
    CartDetails findByDishId(Long dishId);
    List<CartDetails> getCartDetailsByCartId(Long cartId);
}
