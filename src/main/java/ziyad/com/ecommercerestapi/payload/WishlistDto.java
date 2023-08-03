package ziyad.com.ecommercerestapi.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.User;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class WishlistDto {
    private Long id;
    private User user;
    private Product product;
}
