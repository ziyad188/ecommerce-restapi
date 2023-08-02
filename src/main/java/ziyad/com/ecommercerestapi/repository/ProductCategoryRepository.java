package ziyad.com.ecommercerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ziyad.com.ecommercerestapi.entity.ProductCategory;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
}
