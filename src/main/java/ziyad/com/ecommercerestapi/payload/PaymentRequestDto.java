package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
    private String type;
    private String cardName;
    private String cardNumber;
    private String data;
    private int expiryYear;
    private int expiryMonth;
    private int cvc;
}
