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
public class ResponseWishlistDto {
    @Schema(description = "ID of the wishlist")
    private Long id;

    @Schema(description = "ID of the user who owns the wishlist")
    private Long userId;

    @Schema(description = "Name of the user who owns the wishlist")
    private String userName;

    @Schema(description = "ID of the product in the wishlist")
    private Long productId;

    @Schema(description = "Name of the product in the wishlist")
    private String productName;

    @Schema(description = "URL of the product image in the wishlist")
    private String imageUrl;

    @Schema(description = "Amount of the product in the wishlist")
    private BigDecimal amount;
}
