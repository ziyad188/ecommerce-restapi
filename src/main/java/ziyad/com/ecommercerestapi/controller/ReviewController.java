package ziyad.com.ecommercerestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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
    //create review
    @PostMapping("{uid}/add/{pid}")
    public ResponseEntity<ResponseReviewDto> createReview(@PathVariable("pid") Long productId,@PathVariable("uid") Long userId,@RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(productId,userId,reviewDto), HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<ResponseReviewDto> getReview(@PathVariable("id") Long reviewId){
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    //update review
    @PutMapping("{id}")
    public ResponseEntity<ResponseReviewDto> updateReview(@PathVariable("id") Long reviewId,@RequestBody ReviewDto reviewDto){
        return ResponseEntity.ok(reviewService.updateReview(reviewId,reviewDto));
    }
    //delete review
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id")Long reviewId){
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted sucessfully");
    }
    //get review of each products
    @GetMapping("products/{id}")
    public ResponseEntity<List<ResponseReviewDto>> getReviewForProduct(@PathVariable("id")Long productId){
        return ResponseEntity.ok(reviewService.getProductReviews(productId));
    }
}
