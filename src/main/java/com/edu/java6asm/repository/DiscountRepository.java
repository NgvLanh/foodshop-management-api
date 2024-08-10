package com.edu.java6asm.repository;

import com.edu.java6asm.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findByCode(String code);
}
