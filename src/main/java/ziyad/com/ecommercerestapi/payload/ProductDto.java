package ziyad.com.ecommercerestapi.payload;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {
    private Long id;

    private Long categoryId;

    private String sku;

    private String name;

    private String description;

    private BigDecimal unitPrice;

    private String imageUrl;

    private Boolean active;

    private int unitsInStock;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;
}
