package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {
    @Schema(description = "ID of the coupon")
    private Long id;

    @Schema(description = "Coupon code")
    private String couponCode;

    @Schema(description = "Discount percentage")
    private BigDecimal discountPercentage;

    @Schema(description = "Expiry date of the coupon")
    private LocalDate expiryDate;
}
