package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAddressDto {
    private Long id;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private Long userId;
}
