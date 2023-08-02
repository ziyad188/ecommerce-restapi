package ziyad.com.ecommercerestapi.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ziyad.com.ecommercerestapi.entity.ProductCategory;

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
