package ziyad.com.ecommercerestapi.payload;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {
    @Schema(description = "ID of the product")
    private Long id;

    @Schema(description = "ID of the category to which the product belongs")
    private Long categoryId;

    @Schema(description = "Stock Keeping Unit (SKU) of the product")
    private String sku;

    @Schema(description = "Name of the product")
    private String name;

    @Schema(description = "Description of the product")
    private String description;

    @Schema(description = "Unit price of the product")
    private BigDecimal unitPrice;

    @Schema(description = "URL of the product image")
    private String imageUrl;

    @Schema(description = "Indicates whether the product is active or not")
    private Boolean active;

    @Schema(description = "Number of units of the product in stock")
    private int unitsInStock;

    @Schema(description = "Date and time when the product was created")
    private LocalDateTime dateCreated;

    @Schema(description = "Date and time when the product was last updated")
    private LocalDateTime lastUpdated;
}
