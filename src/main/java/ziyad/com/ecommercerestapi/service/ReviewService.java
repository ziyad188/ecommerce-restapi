package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.entity.Review;
import ziyad.com.ecommercerestapi.payload.ResponseReviewDto;
import ziyad.com.ecommercerestapi.payload.ReviewDto;

import java.util.List;

public interface ReviewService {
 ResponseReviewDto createReview(Long productId, Long userId, ReviewDto reviewDto);
    ResponseReviewDto getReviewById(Long reviewId);
    ResponseReviewDto updateReview(Long reviewId,ReviewDto reviewDto);
 void deleteReview(Long reviewId);
 List<ResponseReviewDto> getProductReviews(Long productId);
}
