package ziyad.com.ecommercerestapi.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ziyad.com.ecommercerestapi.payload.ResponseReviewDto;
import ziyad.com.ecommercerestapi.payload.ReviewDto;
import ziyad.com.ecommercerestapi.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(
            summary = "Create a review",
            description = "Create a review for a specific product."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Review created successfully"
    )
    @PostMapping("{uid}/add/{pid}")
    public ResponseEntity<ResponseReviewDto> createReview(
            @PathVariable("pid") Long productId,
            @PathVariable("uid") Long userId,
            @RequestBody ReviewDto reviewDto
    ) {
        return new ResponseEntity<>(reviewService.createReview(productId, userId, reviewDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get a review",
            description = "Get a review by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Review found"
    )
    @GetMapping("{id}")
    public ResponseEntity<ResponseReviewDto> getReview(@PathVariable("id") Long reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    @Operation(
            summary = "Update a review",
            description = "Update a review by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Review updated successfully"
    )
    @PutMapping("{id}")
    public ResponseEntity<ResponseReviewDto> updateReview(
            @PathVariable("id") Long reviewId,
            @RequestBody ReviewDto reviewDto
    ) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, reviewDto));
    }

    @Operation(
            summary = "Delete a review",
            description = "Delete a review by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Review deleted successfully"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }

    @Operation(
            summary = "Get all reviews for a product",
            description = "Get all reviews for a specific product by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Reviews found"
    )
    @GetMapping("products/{id}")
    public ResponseEntity<List<ResponseReviewDto>> getReviewForProduct(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(reviewService.getProductReviews(productId));
    }
}
