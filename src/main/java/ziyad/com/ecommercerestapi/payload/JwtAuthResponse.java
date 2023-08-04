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
public class JwtAuthResponse {
    @Schema(description = "Access token for authentication")
    private String accessToken;

    @Schema(description = "Token type, usually 'Bearer'")
    private String tokenType = "Bearer";
}
