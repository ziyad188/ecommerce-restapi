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
public class SignUpDto {
    @Schema(description = "Username of the user")
    private String username;

    @Schema(description = "Email address of the user")
    private String email;

    @Schema(description = "Password of the user")
    private String password;
}
