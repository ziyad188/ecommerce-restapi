package ziyad.com.ecommercerestapi.service.impl;

import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.Review;
import ziyad.com.ecommercerestapi.entity.User;
import ziyad.com.ecommercerestapi.exception.ResourceNotFoundException;
import ziyad.com.ecommercerestapi.payload.ResponseReviewDto;
import ziyad.com.ecommercerestapi.payload.ReviewDto;
import ziyad.com.ecommercerestapi.repository.ProductRepository;
import ziyad.com.ecommercerestapi.repository.ReviewRepository;
import ziyad.com.ecommercerestapi.repository.UserRepository;
import ziyad.com.ecommercerestapi.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private ModelMapper mapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository, ModelMapper mapper) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
//create review
    @Override
    public ResponseReviewDto createReview(Long productId, Long userId, ReviewDto reviewDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Product product = productRepository
                .findById(productId).orElseThrow(()->new ResourceNotFoundException("Product","id",productId));
        Review review = mapper.map(reviewDto,Review.class);
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
        review.setUser(user);
        review.setProduct(product);
        Review savedReview = reviewRepository.save(review);
        //response
        ResponseReviewDto responseReviewDto = new ResponseReviewDto();
        responseReviewDto.setId(savedReview.getId());
        responseReviewDto.setUserId(savedReview.getUser().getId());
        responseReviewDto.setProductId(savedReview.getProduct().getId());
        responseReviewDto.setRating(review.getRating());
        responseReviewDto.setComment(review.getComment());
        return responseReviewDto;
    }
//get review
    @Override
    public ResponseReviewDto getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ResourceNotFoundException("Review","id",reviewId));
        ResponseReviewDto responseReviewDto = new ResponseReviewDto();
        responseReviewDto.setId(reviewId);
        responseReviewDto.setUserId(review.getUser().getId());
        responseReviewDto.setProductId(review.getProduct().getId());
        responseReviewDto.setRating(review.getRating());
        responseReviewDto.setComment(review.getComment());
        return responseReviewDto;
    }
//update  Review
    @Override
    public ResponseReviewDto updateReview(Long reviewId, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()->new ResourceNotFoundException("Review","id",reviewId));
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setId(reviewId);
        review.setUser(review.getUser());
        review.setProduct(review.getProduct());
        reviewRepository.save(review);
        //create response
        ResponseReviewDto responseReviewDto = new ResponseReviewDto();
        responseReviewDto.setId(reviewId);
        responseReviewDto.setUserId(review.getUser().getId());
        responseReviewDto.setProductId(review.getProduct().getId());
        responseReviewDto.setRating(review.getRating());
        responseReviewDto.setComment(review.getComment());
        return responseReviewDto;
    }
//delete
    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()->new ResourceNotFoundException("Review","id",reviewId));
        reviewRepository.delete(review);
    }
//get product reviews for admin
    @Override
    public List<ResponseReviewDto> getProductReviews(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Porduct","id",productId));
        List<Review> reviews = reviewRepository.findByProduct(product);
        ResponseReviewDto responseReviewDto = new ResponseReviewDto();
        List<ResponseReviewDto> responseReviewDtos = reviews.stream()
                .map(review -> {
                    responseReviewDto.setId(review.getId());
                    responseReviewDto.setUserId(review.getUser().getId());
                    responseReviewDto.setProductId(review.getProduct().getId());
                    responseReviewDto.setComment(review.getComment());
                    responseReviewDto.setRating(review.getRating());
                    return responseReviewDto;
                }).collect(Collectors.toList());
        return responseReviewDtos;
    }
}
