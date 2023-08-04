package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCartDto {
    @Schema(description = "ID of the cart")
    private Long cartId;

    @Schema(description = "ID of the user who owns the cart")
    private Long userId;

    @Schema(description = "ID of the product in the cart")
    private Long productId;

    @Schema(description = "Name of the product in the cart")
    private String productName;

    @Schema(description = "Description of the product in the cart")
    private String productDescription;

    @Schema(description = "Unit price of the product in the cart")
    private BigDecimal productUnitPrice;

    @Schema(description = "URL of the product image in the cart")
    private String productImageUrl;

    @Schema(description = "Quantity of the product in the cart")
    private int quantity;
}
