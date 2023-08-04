package ziyad.com.ecommercerestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAddressDto {
    @Schema(description = "ID of the address")
    private Long id;

    @Schema(description = "Street address of the user")
    private String streetAddress;

    @Schema(description = "City of the user")
    private String city;

    @Schema(description = "State of the user")
    private String state;

    @Schema(description = "Zip code of the user")
    private String zipCode;

    @Schema(description = "ID of the user associated with the address")
    private Long userId;
}
