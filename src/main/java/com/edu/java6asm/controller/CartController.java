package com.edu.java6asm.controller;

import com.edu.java6asm.model.Cart;
import com.edu.java6asm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/{userId}")
    public ResponseEntity<?> findCartByUserId(@PathVariable Long userId) {
        var cart = cartService.findCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody Cart cart) {
        var createCart = cartService.createCart(cart);
        return ResponseEntity.ok(createCart);
    }
}
