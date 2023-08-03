package ziyad.com.ecommercerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.User;
import ziyad.com.ecommercerestapi.entity.Wishlist;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    List<Wishlist> findByUser(User user);
    Wishlist findByUserAndProduct(User user, Product product);

}
