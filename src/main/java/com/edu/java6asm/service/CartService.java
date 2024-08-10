package com.edu.java6asm.service;

import com.edu.java6asm.model.Cart;
import com.edu.java6asm.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart findCartByUserId(Long userId) {
        return cartRepository.findCartByUserId(userId);
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

}
