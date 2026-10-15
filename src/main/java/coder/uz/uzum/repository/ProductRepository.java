package coder.uz.uzum.repository;
import coder.uz.uzum.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
  Optional<Product> findById(UUID id);

  @Modifying
  @Query("update Product p set p.quantity = p.quantity - 1 where p.id = :id and p.quantity > 0")
  int decrementQuantityIfAvailable(@Param("id") UUID id);
}
