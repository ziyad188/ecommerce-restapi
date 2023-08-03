package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.User;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCartDto {
    private Long cartId;
    private Long userId;
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productUnitPrice;
    private String productImageUrl;
    private int quantity;
}
