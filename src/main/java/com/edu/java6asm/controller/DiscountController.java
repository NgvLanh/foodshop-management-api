package com.edu.java6asm.controller;

import com.edu.java6asm.model.Discount;
import com.edu.java6asm.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping
    public ResponseEntity<?> getAllDiscount() {
        var discounts = discountService.getAllDiscount();
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getDiscountByCode(@PathVariable String code) {
        var discount = discountService.getDiscountByCode(code);
        if (discount == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(discount);
    }

    @PostMapping
    public ResponseEntity<?> createDiscount(@RequestBody Discount discount) {
        var createDiscount = discountService.createDiscount(discount);
        return ResponseEntity.ok(createDiscount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.ok().build();
    }
}
