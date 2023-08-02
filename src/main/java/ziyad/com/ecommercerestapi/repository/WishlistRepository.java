package ziyad.com.ecommercerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ziyad.com.ecommercerestapi.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
}
