package ziyad.com.ecommercerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ziyad.com.ecommercerestapi.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
