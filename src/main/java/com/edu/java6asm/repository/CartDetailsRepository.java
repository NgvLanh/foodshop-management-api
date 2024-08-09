package com.edu.java6asm.repository;

import com.edu.java6asm.model.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {
}
