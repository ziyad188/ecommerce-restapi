package ziyad.com.ecommercerestapi.controller;

import lombok.Getter;
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
    //create coupon

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponDto couponDto){
        return new  ResponseEntity<>(couponService.createCoupon(couponDto), HttpStatus.CREATED);
    }
    //get by id

    @GetMapping("{id}")
    public ResponseEntity<CouponDto> getCoupon(@PathVariable("id") Long couponId){
        return ResponseEntity.ok(couponService.getCouponById(couponId));
    }
    //get all coupon
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<List<CouponDto>> getAllCoupon(){
        return ResponseEntity.ok(couponService.getAllCoupon());
    }
    //update Coupon
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<CouponDto> updateCoupon(@RequestBody CouponDto couponDto,@PathVariable("id") Long couponId){
        return ResponseEntity.ok(couponService.updateCoupon(couponDto,couponId));
    }
    //delete Coupon
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCoupon(@PathVariable("id") Long couponId){
        couponService.deleteCoupon(couponId);
        return ResponseEntity.ok("Coupon deleted sucessfully");
    }
}
