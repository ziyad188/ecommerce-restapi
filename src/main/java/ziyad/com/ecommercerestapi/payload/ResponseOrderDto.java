package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDto {
    @Schema(description = "ID of the order")
    private Long id;

    @Schema(description = "ID of the user who placed the order")
    private Long userId;

    @Schema(description = "Date and time when the order was placed")
    private LocalDateTime orderDate;

    @Schema(description = "Total price of the order")
    private BigDecimal totalPrice;

    @Schema(description = "Status of the order")
    private String orderStatus;

    @Schema(description = "List of order items")
    private List<OrderItemDto> orderItems;

    @Schema(description = "Billing address of the order")
    private AddressDto billingAddress;

//     @Schema(description = "Client secret for payment (if applicable)")
//     private String clientSecret;
//
//     @Schema(description = "ID of the payment intent (if applicable)")
//     private String intentId;
}
