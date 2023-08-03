package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.payload.ResponseWishlistDto;
import ziyad.com.ecommercerestapi.payload.WishlistDto;

import java.util.List;

public interface WishlistService {
    ResponseWishlistDto addToWishlist(Long userId, Long productId);
    void removeFromWishlist(Long wishlistId);
    List<ResponseWishlistDto>getUserWishlist(Long userId);

}
