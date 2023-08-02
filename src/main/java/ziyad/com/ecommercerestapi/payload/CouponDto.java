package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {
    private Long id;
    private String couponCode;
    private BigDecimal discountPercentage;

    private LocalDate expiryDate;
}
