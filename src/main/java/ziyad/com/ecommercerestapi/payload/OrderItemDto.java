package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    @Schema(description = "ID of the product in the order")
    private Long productId;

    @Schema(description = "Name of the product in the order")
    private String productName;

    @Schema(description = "Quantity of the product in the order")
    private int quantity;
}
