package com.fliper.e_commerceApp.controller;

import com.fliper.e_commerceApp.model.Product;
import com.fliper.e_commerceApp.service.ProductService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/addProduct")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if(product.isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.status(200).body("Deleted successfully ");
        }else{
            return ResponseEntity.status(404).body("Product Id not exist");
        }
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok("Product updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Product not found.");
        }
    }
}
