package ziyad.com.ecommercerestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ziyad.com.ecommercerestapi.payload.ResponseWishlistDto;
import ziyad.com.ecommercerestapi.payload.WishlistDto;
import ziyad.com.ecommercerestapi.service.WishlistService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlists")
public class WishlistCotroller {
    private WishlistService wishlistService;

    public WishlistCotroller(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

//add to wishlist
    @PostMapping("{uid}/add/{pid}")
    public ResponseEntity<ResponseWishlistDto> addToWishlist(@PathVariable("pid") Long productId, @PathVariable("uid") Long userid){
        return new ResponseEntity<>(wishlistService.addToWishlist(userid,productId), HttpStatus.CREATED);
    }
    //remove from wishlist
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWishlist(@PathVariable("id") Long wishlistId){
        wishlistService.removeFromWishlist(wishlistId);
        return ResponseEntity.ok("Wishlist deleted successfully");
    }
    //get user wishlist
    @GetMapping("{id}")
    public ResponseEntity<List<ResponseWishlistDto>> getUserWishlist(@PathVariable("id") Long userId){
        return ResponseEntity.ok(wishlistService.getUserWishlist(userId));
    }


}
