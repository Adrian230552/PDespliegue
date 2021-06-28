package com.challenge.longlife.controller;

import com.challenge.longlife.domain.model.Category;
import com.challenge.longlife.domain.service.CategoryService;
import com.challenge.longlife.resource.BigTreeResource;
import com.challenge.longlife.resource.CategoryResource;
import com.challenge.longlife.resource.SaveCategoryResource;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get all Categories", description = "Get All Categories by Pages", tags = {"categories"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Categories returned", content = @Content(mediaType = "application/json"))
    })

    @GetMapping("/categories")
    public Page<CategoryResource> getAllCategories(Pageable pageable) {
        Page<Category> categoriesPage = categoryService.getAllCategories(pageable);
        List<CategoryResource> resources = categoriesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Create Category", description = "Create a new Categories", tags = {"categories"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a new Categories", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/categories")
    public CategoryResource createCategory(@Valid @RequestBody SaveCategoryResource resource) {
        Category category = convertToEntity(resource);
        return convertToResource(categoryService.createCategory(category));
    }
    @Operation(summary = "Update Category By ID", description = "Update Category by ID", tags = {"categories"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category returned", content = @Content(mediaType = "application/json"))

    })
    @PutMapping("/categories/{categoryId}")
    public CategoryResource updateCategory(@PathVariable Long categoryId, @Valid @RequestBody SaveCategoryResource resource) {
        Category category = convertToEntity(resource);
        return convertToResource(categoryService.updateCategory(categoryId, category));
    }
    @Operation(summary = "Delete Category By ID", description = "Delete Category by ID", tags = {"categories"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted", content = @Content(mediaType = "application/json"))

    })
    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
    @Operation(summary = "Get Category By ID", description = "Get Category by ID", tags = {"categories"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category returned", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/categories/{categoryId}")
    public CategoryResource getCategoryById(
            @PathVariable Long categoryId) {
        return convertToResource(categoryService.getCategoryById(categoryId));
    }
    private Category convertToEntity(SaveCategoryResource resource) {
        return mapper.map(resource, Category.class);
    }
    private CategoryResource convertToResource(Category entity) {
        return mapper.map(entity, CategoryResource.class);
    }

}
