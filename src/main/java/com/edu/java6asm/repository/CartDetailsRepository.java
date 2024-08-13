package com.edu.java6asm.repository;

import com.edu.java6asm.model.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {
    CartDetails findByDishIdAndCartId(Long dishId, Long cartId);
    List<CartDetails> getCartDetailsByCartId(Long cartId);
}
