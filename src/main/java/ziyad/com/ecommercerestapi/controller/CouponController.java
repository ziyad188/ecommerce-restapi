package ziyad.com.ecommercerestapi.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ziyad.com.ecommercerestapi.payload.CouponDto;
import ziyad.com.ecommercerestapi.service.CouponService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {
    private CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @Operation(
            summary = "Create a coupon",
            description = "Create a new coupon."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Coupon created successfully"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponDto couponDto) {
        return new ResponseEntity<>(couponService.createCoupon(couponDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get a coupon by ID",
            description = "Get a coupon by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Coupon found"
    )
    @GetMapping("{id}")
    public ResponseEntity<CouponDto> getCoupon(@PathVariable("id") Long couponId) {
        return ResponseEntity.ok(couponService.getCouponById(couponId));
    }

    @Operation(
            summary = "Get all coupons",
            description = "Get all coupons."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Coupons found"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CouponDto>> getAllCoupon() {
        return ResponseEntity.ok(couponService.getAllCoupon());
    }

    @Operation(
            summary = "Update a coupon",
            description = "Update a coupon by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Coupon updated successfully"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CouponDto> updateCoupon(
            @RequestBody CouponDto couponDto,
            @PathVariable("id") Long couponId
    ) {
        return ResponseEntity.ok(couponService.updateCoupon(couponDto, couponId));
    }

    @Operation(
            summary = "Delete a coupon",
            description = "Delete a coupon by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Coupon deleted successfully"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCoupon(@PathVariable("id") Long couponId) {
        couponService.deleteCoupon(couponId);
        return ResponseEntity.ok("Coupon deleted successfully");
    }
}
