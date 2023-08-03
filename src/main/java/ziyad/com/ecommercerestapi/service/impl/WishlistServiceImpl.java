package ziyad.com.ecommercerestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.User;
import ziyad.com.ecommercerestapi.entity.Wishlist;
import ziyad.com.ecommercerestapi.exception.ResourceNotFoundException;
import ziyad.com.ecommercerestapi.payload.ResponseWishlistDto;
import ziyad.com.ecommercerestapi.payload.WishlistDto;
import ziyad.com.ecommercerestapi.repository.ProductRepository;
import ziyad.com.ecommercerestapi.repository.UserRepository;
import ziyad.com.ecommercerestapi.repository.WishlistRepository;
import ziyad.com.ecommercerestapi.service.WishlistService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {
    private WishlistRepository wishlistRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private ModelMapper mapper;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository, ProductRepository productRepository,ModelMapper mapper) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.mapper =mapper;
    }

    @Override
    public ResponseWishlistDto addToWishlist(Long userId, Long productId) {
        // Retrieve the user and product entities from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        Wishlist existingWishlist = wishlistRepository.findByUserAndProduct(user, product);
        if (existingWishlist != null) {
            throw new RuntimeException("This product is already in the wishlist for the user.");
        }
        Wishlist wishlist = new Wishlist();
            wishlist.setUser(user);
            wishlist.setProduct(product);
            Wishlist savesWishlist =wishlistRepository.save(wishlist);
        // create response dto
        ResponseWishlistDto responseWishlistDto = new ResponseWishlistDto();
        responseWishlistDto.setId(savesWishlist.getId());
        responseWishlistDto.setUserName(savesWishlist.getUser().getUsername());
        responseWishlistDto.setUserId(savesWishlist.getUser().getId());
        responseWishlistDto.setProductName(savesWishlist.getProduct().getName());
        responseWishlistDto.setProductId(savesWishlist.getProduct().getId());
        responseWishlistDto.setImageUrl(savesWishlist.getProduct().getImageUrl());
        responseWishlistDto.setAmount(savesWishlist.getProduct().getUnitPrice());
        return responseWishlistDto;
    }


    @Override
    public void removeFromWishlist(Long wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(()-> new ResourceNotFoundException("Wishlist","id",wishlistId));
        wishlistRepository.delete(wishlist);

    }

    @Override
    public List<ResponseWishlistDto> getUserWishlist(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        List<Wishlist> wishlists = wishlistRepository.findByUser(user);
        ResponseWishlistDto responseWishlistDto = new ResponseWishlistDto();
        return wishlists.stream()
                .map((wishlist -> {
                    responseWishlistDto.setId(wishlist.getId());
                    responseWishlistDto.setUserName(wishlist.getUser().getUsername());
                    responseWishlistDto.setUserId(wishlist.getUser().getId());
                    responseWishlistDto.setProductName(wishlist.getProduct().getName());
                    responseWishlistDto.setProductId(wishlist.getProduct().getId());
                    responseWishlistDto.setImageUrl(wishlist.getProduct().getImageUrl());
                    responseWishlistDto.setAmount(wishlist.getProduct().getUnitPrice());
                    return responseWishlistDto;
                })).collect(Collectors.toList());
    }
}
