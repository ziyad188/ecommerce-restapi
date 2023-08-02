package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.payload.CouponDto;

import java.util.List;

public interface CouponService {
    CouponDto createCoupon(CouponDto couponDto);
    CouponDto getCouponById(Long couponId);
    List<CouponDto> getAllCoupon();
    CouponDto updateCoupon(CouponDto couponDto,Long couponId);
    void deleteCoupon(Long CouponId);
}
