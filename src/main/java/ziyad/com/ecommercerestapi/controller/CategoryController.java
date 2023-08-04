package ziyad.com.ecommercerestapi.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ziyad.com.ecommercerestapi.payload.CategoryDto;
import ziyad.com.ecommercerestapi.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Create a category",
            description = "Create a new category."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Category created successfully"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get a category by ID",
            description = "Get a category by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category found"
    )
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @Operation(
            summary = "Get all categories",
            description = "Get all categories."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Categories found"
    )
    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @Operation(
            summary = "Update a category",
            description = "Update a category by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category updated successfully"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto,
            @PathVariable("id") Long categoryId
    ) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
    }

    @Operation(
            summary = "Delete a category",
            description = "Delete a category by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category deleted successfully"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
