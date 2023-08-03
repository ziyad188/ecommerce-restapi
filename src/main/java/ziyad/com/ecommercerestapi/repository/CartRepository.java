package ziyad.com.ecommercerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ziyad.com.ecommercerestapi.entity.Cart;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.User;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUserAndProduct(User user, Product product);

    List<Cart> findByUser(User user);
    List<Cart> findByProduct(Product product);

    void deleteByUser(User user);
}
