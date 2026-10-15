
package coder.uz.uzum.repository;

import coder.uz.uzum.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findById(UUID id);
    Optional<Order> findByUserId(UUID userId);
}
