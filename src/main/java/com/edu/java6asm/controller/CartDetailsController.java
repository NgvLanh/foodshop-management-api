package com.edu.java6asm.controller;

import com.edu.java6asm.model.CartDetails;
import com.edu.java6asm.service.CartDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-details")
public class CartDetailsController {
    @Autowired
    private CartDetailsService cartDetailsService;

    @GetMapping("/carts/{cardId}")
    public ResponseEntity<?> getCartDetailsByCartId(@PathVariable Long cardId) {
        var cartDetails = cartDetailsService.getCartDetailsByCartId(cardId);
        return ResponseEntity.ok(cartDetails);
    }
    @PostMapping
    public ResponseEntity<?> createCartDetails(@RequestBody CartDetails cartDetails) {
        var createCartDetails = cartDetailsService.createCartDetails(cartDetails);
        return ResponseEntity.ok(createCartDetails);
    }

    @GetMapping("/{dishId}/{cartId}")
    public ResponseEntity<?> existsCartDetails(@PathVariable Long dishId,
                                               @PathVariable Long cartId) {
        var createCartDetails = cartDetailsService.existsCartDetails(dishId, cartId);
        return ResponseEntity.ok(createCartDetails);
    }
    @PutMapping("/{dishId}/{cartId}")
    public  ResponseEntity<?> updateQuantityByDish(@PathVariable Long dishId,
                                                   @PathVariable Long cartId,
                                                   @RequestBody CartDetails updatedCartDetails
                                                   ) {
        var updated = cartDetailsService.updateQuantityByDish(dishId, cartId, updatedCartDetails);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/update-quantity/{cartDetailsId}")
    public  ResponseEntity<?> updateQuantityCartDetails(@PathVariable Long cartDetailsId,
                                                   @RequestBody CartDetails updatedCartDetails) {
        var updated = cartDetailsService.updateQuantityCartDetails(cartDetailsId, updatedCartDetails);
        return ResponseEntity.ok(updated);
    }
    @PutMapping("/update-selected/{cartDetailsId}")
    public  ResponseEntity<?> updateSelectedCartDetails(@PathVariable Long cartDetailsId) {
        var updated = cartDetailsService.updateSelectedCartDetails(cartDetailsId);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{cartDetailsId}")
    public  ResponseEntity<?> deleteCartDetails(@PathVariable Long cartDetailsId) {
        cartDetailsService.deleteCartDetails(cartDetailsId);
        return ResponseEntity.noContent().build();
    }
}
