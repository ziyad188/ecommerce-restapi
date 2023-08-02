package ziyad.com.ecommercerestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ziyad.com.ecommercerestapi.entity.Coupon;
import ziyad.com.ecommercerestapi.exception.ProductApiException;
import ziyad.com.ecommercerestapi.exception.ResourceNotFoundException;
import ziyad.com.ecommercerestapi.payload.CouponDto;
import ziyad.com.ecommercerestapi.repository.CouponRepository;
import ziyad.com.ecommercerestapi.service.CouponService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CouponServiceImpl implements CouponService {
    private CouponRepository couponRepository;
    private ModelMapper mapper;

    public CouponServiceImpl(CouponRepository couponRepository, ModelMapper mapper) {
        this.couponRepository = couponRepository;
        this.mapper = mapper;
    }

    @Override
    public CouponDto createCoupon(CouponDto couponDto) {
        Coupon coupon = mapper.map(couponDto,Coupon.class);
        Coupon savedCoupon = couponRepository.save(coupon);
        return mapper.map(savedCoupon,CouponDto.class);
    }

    @Override
    public CouponDto getCouponById(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(()-> new ResourceNotFoundException("Coupon","id",couponId));
        LocalDate  currentDate = LocalDate.now();
        Boolean active =coupon.getExpiryDate().isAfter(currentDate);
        if(active){
            return mapper.map(coupon,CouponDto.class);
        }else{
            throw new ProductApiException(HttpStatus.NOT_ACCEPTABLE,"Coupon expired");
        }

    }

    @Override
    public List<CouponDto> getAllCoupon() {
        List<Coupon> coupons = couponRepository.findAll();

        return coupons.stream()
                .map((coupon ->mapper.map(coupon,CouponDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public CouponDto updateCoupon(CouponDto couponDto, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(()-> new ResourceNotFoundException("Coupon","id",couponId));
        coupon.setCouponId(couponId);
        coupon.setDiscountPercentage(couponDto.getDiscountPercentage());
        coupon.setCouponCode(couponDto.getCouponCode());
        coupon.setExpiryDate(couponDto.getExpiryDate());
        Coupon savedCoupon = couponRepository.save(coupon);


        return mapper.map(savedCoupon,CouponDto.class);
    }

    @Override
    public void deleteCoupon(Long CouponId) {
        Coupon coupon = couponRepository.findById(CouponId)
                .orElseThrow(()-> new ResourceNotFoundException("Coupon","id",CouponId));
        couponRepository.delete(coupon);
    }
}
