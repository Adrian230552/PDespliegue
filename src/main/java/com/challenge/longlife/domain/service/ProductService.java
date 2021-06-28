package com.challenge.longlife.domain.service;


import com.challenge.longlife.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable);

    Product getProductById(Long productId);

    Product createProduct(Product product);

    Product updateProduct(Long productId, Product product);

    ResponseEntity<?> deleteProduct(Long productId);
}
