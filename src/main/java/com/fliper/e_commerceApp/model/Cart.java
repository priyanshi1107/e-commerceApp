package com.fliper.e_commerceApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ElementCollection
    private List<Long> productIds;

    private Double totalAmount;

    // Add methods to add and remove products from the cart
    public void addProduct(Long productId) {
        productIds.add(productId);
        // Update totalAmount logic here
    }

    public void removeProduct(Long productId) {
        productIds.remove(productId);
        // Update totalAmount logic here
    }
}
