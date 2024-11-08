package com.fliper.e_commerceApp.service;

import com.fliper.e_commerceApp.dao.CartRepository;
import com.fliper.e_commerceApp.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart addProductToCart(Cart cart) {
        Optional<Cart> existingCart = cartRepository.findByUserId(cart.getUserId());
        if (existingCart.isPresent()) {
            Cart updatedCart = existingCart.get();
            updatedCart.addProduct(cart.getProductIds().get(0)); // Assuming you add one product at a time
            return cartRepository.save(updatedCart);
        } else {
            return cartRepository.save(cart);
        }
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElse(null);
    }

    public void clearCart(Long userId) {
        cartRepository.findByUserId(userId).ifPresent(cart -> {
            cartRepository.delete(cart);
        });
    }

    public void removeProductFromCart(Long productId) {
        cartRepository.findAll().forEach(cart -> {
            cart.removeProduct(productId);
            cartRepository.save(cart);
        });
    }
}
