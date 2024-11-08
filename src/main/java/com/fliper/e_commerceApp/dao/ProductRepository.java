package com.fliper.e_commerceApp.dao;

import com.fliper.e_commerceApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
