package com.challenge.longlife.service;

import com.challenge.longlife.domain.model.Category;
import com.challenge.longlife.domain.model.Product;
import com.challenge.longlife.domain.repository.CategoryRepository;
import com.challenge.longlife.domain.repository.ProductRepository;
import com.challenge.longlife.domain.service.CategoryService;
import com.challenge.longlife.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, Category categoryRequest) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        return categoryRepository.save(
                category.setDescription(categoryRequest.getDescription())
                        .setName(categoryRequest.getName()));
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }
}