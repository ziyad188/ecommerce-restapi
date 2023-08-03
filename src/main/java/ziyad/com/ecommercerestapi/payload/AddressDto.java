package ziyad.com.ecommercerestapi.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private Long id;
    private Long userId;

    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
}
