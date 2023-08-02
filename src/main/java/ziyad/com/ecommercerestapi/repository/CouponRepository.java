package ziyad.com.ecommercerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ziyad.com.ecommercerestapi.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
}
