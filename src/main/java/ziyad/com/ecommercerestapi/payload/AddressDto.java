package ziyad.com.ecommercerestapi.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ziyad.com.ecommercerestapi.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @Schema(description = "ID of the address")
    private Long id;

    @Schema(description = "ID of the user associated with the address")
    private Long userId;

    @Schema(description = "Street address of the user")
    private String streetAddress;

    @Schema(description = "City of the user")
    private String city;

    @Schema(description = "State of the user")
    private String state;

    @Schema(description = "Zip code of the user")
    private String zipCode;
}
