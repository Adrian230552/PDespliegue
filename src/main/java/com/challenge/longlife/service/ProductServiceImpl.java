package com.challenge.longlife.service;

import com.challenge.longlife.domain.model.Product;
import com.challenge.longlife.domain.repository.CategoryRepository;
import com.challenge.longlife.domain.repository.ProductRepository;
import com.challenge.longlife.domain.repository.SupplierRepository;
import com.challenge.longlife.domain.service.ProductService;
import com.challenge.longlife.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Product getProductById(Long productId) {
        return (Product) this.productRepository.findById(productId).orElseThrow(() -> {
            return new ResourceNotFoundException("Product", "Id", productId);
        });
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product productDetails) {
        return (Product) this.productRepository.findById(productId).map((product) -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setDescription(productDetails.getDescription());
            return (Product)this.productRepository.save(product);
        }).orElseThrow(() -> {
            return new ResourceNotFoundException("Product", "Id", productId);
        });
    }

    public ResponseEntity<?> deleteProduct(Long productId) {
        return (ResponseEntity)this.productRepository.findById(productId).map((product) -> {
            this.productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> {
            return new ResourceNotFoundException("Product", "Id", productId);
        });
    }
}
