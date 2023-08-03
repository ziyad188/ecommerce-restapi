package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.payload.ResponseCartDto;

import java.util.List;

public interface CartService {
    ResponseCartDto addCart(Long userId,Long productId,int quantity);
    List<ResponseCartDto> getUserCarts(Long userId);
    ResponseCartDto updateCartQuntity(Long cartId,int quantity);
    void removeCartItem(Long cartId);
    void clearUserCart(Long userId);
    double calculateTotalPrice(Long userId);

}
