package ziyad.com.ecommercerestapi.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ziyad.com.ecommercerestapi.payload.ProductDto;
import ziyad.com.ecommercerestapi.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Create a product",
            description = "Create a new product."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Product created successfully"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get a product by ID",
            description = "Get a product by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product found"
    )
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @Operation(
            summary = "Get all products",
            description = "Get all products."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Products found"
    )
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @Operation(
            summary = "Update a product",
            description = "Update a product by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product updated successfully"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(
            @RequestBody ProductDto productDto,
            @PathVariable("id") Long productId
    ) {
        return ResponseEntity.ok(productService.updateProduct(productDto, productId));
    }

    @Operation(
            summary = "Delete a product",
            description = "Delete a product by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product deleted successfully"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
