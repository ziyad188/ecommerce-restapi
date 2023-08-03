package ziyad.com.ecommercerestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import ziyad.com.ecommercerestapi.payload.ResponseAddressDto;
import ziyad.com.ecommercerestapi.payload.ResponseCartDto;
import ziyad.com.ecommercerestapi.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    //add to cart
    @PostMapping()
    public ResponseEntity<ResponseCartDto> addToCart(@RequestParam Long uid, @RequestParam Long pid,@RequestParam int qty){
        return new ResponseEntity<>(cartService.addCart(uid,pid,qty), HttpStatus.CREATED);
    }
    //get user cart
    @GetMapping("{id}")
    public ResponseEntity<List<ResponseCartDto>> getCartByUser(@PathVariable("id") Long userId){
        return ResponseEntity.ok(cartService.getUserCarts(userId));
    }

    //update cart
    @PutMapping("{id}")
    public ResponseEntity<ResponseCartDto> updateCart(@PathVariable("id") Long cartId,@RequestParam int qty){
        return ResponseEntity.ok(cartService.updateCartQuntity(cartId,qty));
    }
    //remove cart item
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("id") Long cartId){
        cartService.removeCartItem(cartId);
        return ResponseEntity.ok("Deleted cart item deleted");
    }
    //clear  user cart
    @DeleteMapping("clear/{id}")
    public ResponseEntity<String> clearCartItem(@PathVariable("id") Long uid){
        cartService.clearUserCart(uid);
        return ResponseEntity.ok("Cleared cart");
    }
    //calculate total price
    @GetMapping("/price/{id}")
    public ResponseEntity<Double> totalCartPrice(@PathVariable("id") Long userId){
        return ResponseEntity.ok(cartService.calculateTotalPrice(userId));
    }

}
