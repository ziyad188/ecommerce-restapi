package ziyad.com.ecommercerestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    //in many to one mapping we have to join table in child becuase here post is child
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    private String sku;

    private String name;

    private String description;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "image_url")
    private String imageUrl;

    private Boolean active;
    @Column(name = "unit_in_stock")
    private int unitsInStock;
    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;


}
