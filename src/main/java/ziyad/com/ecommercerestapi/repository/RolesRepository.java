package ziyad.com.ecommercerestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ziyad.com.ecommercerestapi.entity.Roles;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findByName(String name);
}
