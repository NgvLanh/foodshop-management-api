package com.edu.java6asm.service;

import com.edu.java6asm.model.Discount;
import com.edu.java6asm.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> getAllDiscount() {
        return discountRepository.findAll();
    }

    public Discount getDiscountByCode(String code) {
        return discountRepository.findByCode(code);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }
//    @PreAuthorize("hasRole('ADMIN')")
//    public Discount updateDiscount(Long id,Discount discount) {
//      return discountRepository.findById(id).map(existingDiscount -> {
//          existingDiscount.setCode(discount.getCode());
//          existingDiscount.setQuantity();
//          return discountRepository.save(existingDiscount);
//      }).orElseThrow(() -> new RuntimeException("Discount not found"));
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}


