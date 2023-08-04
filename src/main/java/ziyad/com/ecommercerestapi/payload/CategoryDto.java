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
public class CategoryDto {
    @Schema(description = "ID of the category")
    private Long id;

    @Schema(description = "Name of the category")
    private String categoryName;

    @Schema(description = "URL of the image associated with the category")
    private String imageUrl;
}
