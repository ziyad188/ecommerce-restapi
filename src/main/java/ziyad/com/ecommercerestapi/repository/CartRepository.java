package ziyad.com.ecommercerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ziyad.com.ecommercerestapi.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
