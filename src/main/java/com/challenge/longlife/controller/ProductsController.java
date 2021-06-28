package com.challenge.longlife.controller;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.challenge.longlife.domain.model.GreenLeaf;
import com.challenge.longlife.domain.model.Product;
import com.challenge.longlife.domain.service.CategoryService;
import com.challenge.longlife.domain.service.ProductService;
import com.challenge.longlife.domain.service.SupplierService;
import com.challenge.longlife.resource.ProductResource;
import com.challenge.longlife.resource.SaveProductResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping({"/api"})
public class ProductsController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ModelMapper mapper;

    public ProductsController() {
    }
    @Operation(summary = "Get all Products", description = "Get All Products by Pages", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Products returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping({"/products"})
    public Page<ProductResource> getAllProducts(Pageable pageable) {
        List<ProductResource> products = (List)this.productService.getAllProducts(pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int productCount = products.size();
        return new PageImpl(products, pageable, (long)productCount);
    }

    @Operation(summary = "Get Product by Id", description = "Get Product by Id", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Products returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping({"/products/{id}"})
    public ProductResource getProductById(@PathVariable(name = "id") Long productId) {
        return this.convertToResource(this.productService.getProductById(productId));
    }

    @Operation(summary = "Create new Product", description = "Create new Product", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create new Product", content = @Content(mediaType = "application/json"))
    })
    @PostMapping({"/categories/{categoryId}/suppliers/{supplierId}/products"})
    public ProductResource createProduct(@PathVariable Long categoryId, @PathVariable Long supplierId,@Valid @RequestBody SaveProductResource resource) {
        Product product = convertToEntity(resource);
        product.setCategory(categoryService.getCategoryById(categoryId));
        product.setSupplier(supplierService.getSupplierById(supplierId));
        return convertToResource(productService.createProduct(product));
    }

    @Operation(summary = "Update Product", description = "Update Product by Id", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Product by Id", content = @Content(mediaType = "application/json"))
    })
    @PutMapping({"/products/{id}"})
    public ProductResource updateProduct(@PathVariable(name = "id") Long productId, @Valid @RequestBody SaveProductResource resource) {
        return this.convertToResource(this.productService.updateProduct(productId, this.convertToEntity(resource)));
    }
    @Operation(summary = "Delete Product", description = "Delete Product by Id", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Product by Id", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping({"/products/{productId}"})
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return this.productService.deleteProduct(productId);
    }

    private Product convertToEntity(SaveProductResource resource) {
        return (Product)this.mapper.map(resource, Product.class);
    }

    private ProductResource convertToResource(Product entity) {
        return (ProductResource)this.mapper.map(entity, ProductResource.class);
    }
}