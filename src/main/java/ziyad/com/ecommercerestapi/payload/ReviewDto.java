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
public class ReviewDto {
    @Schema(description = "ID of the review")
    private Long id;

    @Schema(description = "Rating given in the review")
    private int rating;

    @Schema(description = "Comment provided in the review")
    private String comment;
}
