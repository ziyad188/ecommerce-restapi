package ziyad.com.ecommercerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ziyad.com.ecommercerestapi.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryId(Long categoryId);
}
