package com.fliper.e_commerceApp.controller;

import com.fliper.e_commerceApp.model.Cart;
import com.fliper.e_commerceApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        Cart updatedCart = cartService.addProductToCart(cart);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long productId) {
        cartService.removeProductFromCart(productId);
        return ResponseEntity.noContent().build();
    }
}
