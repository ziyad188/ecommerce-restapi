package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDto {
    @Schema(description = "ID of the wishlist")
    private Long id;

    @Schema(description = "User associated with the wishlist")
    private User user;

    @Schema(description = "Product associated with the wishlist")
    private Product product;
}
