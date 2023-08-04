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
public class PaymentRequestDto {
    @Schema(description = "Type of payment (e.g., credit card, debit card, etc.)")
    private String type;

    @Schema(description = "Name on the payment card")
    private String cardName;

    @Schema(description = "Payment card number")
    private String cardNumber;

    @Schema(description = "Additional data related to the payment (e.g., security code, etc.)")
    private String data;

    @Schema(description = "Expiry year of the payment card")
    private int expiryYear;

    @Schema(description = "Expiry month of the payment card")
    private int expiryMonth;

    @Schema(description = "Card Verification Code (CVC) for the payment card")
    private int cvc;
}
