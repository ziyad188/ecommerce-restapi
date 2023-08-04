package ziyad.com.ecommercerestapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ziyad.com.ecommercerestapi.payload.ResponseWishlistDto;
import ziyad.com.ecommercerestapi.payload.WishlistDto;
import ziyad.com.ecommercerestapi.service.WishlistService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlists")
public class WishlistController {
    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Operation(
            summary = "Add a product to the wishlist",
            description = "Add a product to the user's wishlist."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Product added to wishlist successfully"
    )
    @PostMapping("{uid}/add/{pid}")
    public ResponseEntity<ResponseWishlistDto> addToWishlist(
            @PathVariable("pid") Long productId,
            @PathVariable("uid") Long userId
    ) {
        return new ResponseEntity<>(wishlistService.addToWishlist(userId, productId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Remove a product from the wishlist",
            description = "Remove a product from the user's wishlist by wishlist ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Product removed from wishlist successfully"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWishlist(@PathVariable("id") Long wishlistId) {
        wishlistService.removeFromWishlist(wishlistId);
        return ResponseEntity.ok("Wishlist deleted successfully");
    }

    @Operation(
            summary = "Get user's wishlist",
            description = "Get all products in the user's wishlist."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Wishlist retrieved successfully"
    )
    @GetMapping("{id}")
    public ResponseEntity<List<ResponseWishlistDto>> getUserWishlist(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(wishlistService.getUserWishlist(userId));
    }
}
