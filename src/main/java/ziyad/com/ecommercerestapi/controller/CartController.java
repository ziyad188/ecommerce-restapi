package ziyad.com.ecommercerestapi.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ziyad.com.ecommercerestapi.payload.ResponseAddressDto;
import ziyad.com.ecommercerestapi.payload.ResponseCartDto;
import ziyad.com.ecommercerestapi.service.CartService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(
            summary = "Add to cart",
            description = "Add a product to the user's cart."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Product added to cart successfully"
    )
    @PostMapping()
    public ResponseEntity<ResponseCartDto> addToCart(
            @RequestParam Long uid,
            @RequestParam Long pid,
            @RequestParam int qty
    ) {
        return new ResponseEntity<>(cartService.addCart(uid, pid, qty), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get user cart",
            description = "Get all products in the user's cart."
    )
    @ApiResponse(
            responseCode = "200",
            description = "User cart retrieved successfully"
    )
    @GetMapping("{id}")
    public ResponseEntity<List<ResponseCartDto>> getCartByUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(cartService.getUserCarts(userId));
    }

    @Operation(
            summary = "Update cart",
            description = "Update the quantity of a product in the cart."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cart updated successfully"
    )
    @PutMapping("{id}")
    public ResponseEntity<ResponseCartDto> updateCart(
            @PathVariable("id") Long cartId,
            @RequestParam int qty
    ) {
        return ResponseEntity.ok(cartService.updateCartQuntity(cartId, qty));
    }

    @Operation(
            summary = "Remove cart item",
            description = "Remove a product from the cart."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cart item removed successfully"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("id") Long cartId) {
        cartService.removeCartItem(cartId);
        return ResponseEntity.ok("Deleted cart item deleted");
    }

    @Operation(
            summary = "Clear user cart",
            description = "Remove all products from the user's cart."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Cart cleared successfully"
    )
    @DeleteMapping("clear/{id}")
    public ResponseEntity<String> clearCartItem(@PathVariable("id") Long uid) {
        cartService.clearUserCart(uid);
        return ResponseEntity.ok("Cleared cart");
    }

    @Operation(
            summary = "Calculate total cart price",
            description = "Calculate the total price of all products in the user's cart."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Total cart price calculated successfully"
    )
    @GetMapping("/price/{id}")
    public ResponseEntity<BigDecimal> totalCartPrice(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(cartService.calculateTotalPrice(userId));
    }
}

