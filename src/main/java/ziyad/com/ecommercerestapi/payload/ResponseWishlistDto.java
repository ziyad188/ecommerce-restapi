package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWishlistDto {
    private Long id;
    private Long userId;
    private String userName;
    private Long productId;
    private String productName;
    private String ImageUrl;
    private BigDecimal amount;
}
