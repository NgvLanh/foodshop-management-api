package com.edu.java6asm.service;

import com.edu.java6asm.model.Cart;
import com.edu.java6asm.model.CartDetails;
import com.edu.java6asm.repository.CartDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartDetailsService {

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    public List<CartDetails> getCartDetailsByCartId(Long cartId) {
        return cartDetailsRepository.getCartDetailsByCartId(cartId);
    }

    public CartDetails createCartDetails(CartDetails cartDetails) {
        return cartDetailsRepository.save(cartDetails);
    }

    public CartDetails updateQuantityByDish(Long dishId, CartDetails updatedCartDetails) {
        var updated = cartDetailsRepository.findByDishId(dishId);
        updated.setQuantity(updated.getQuantity() + updatedCartDetails.getQuantity());
        return cartDetailsRepository.save(updated);
    }

    public CartDetails existsCartDetails(Long dishId) {
        return cartDetailsRepository.findByDishId(dishId);
    }

    public CartDetails updateQuantityCartDetails(Long cartDetailsId, CartDetails updatedCartDetails) {
        var updated = cartDetailsRepository.findById(cartDetailsId).orElse(null);
        updated.setQuantity(updatedCartDetails.getQuantity());
        return cartDetailsRepository.save(updated);
    }

    public CartDetails updateSelectedCartDetails(Long cartDetailsId) {
        var updated = cartDetailsRepository.findById(cartDetailsId).orElse(null);
        updated.setIsSelected(!updated.getIsSelected());
        return cartDetailsRepository.save(updated);
    }

    public void deleteCartDetails(Long cartDetailsId) {
        cartDetailsRepository.deleteById(cartDetailsId);
    }
}
