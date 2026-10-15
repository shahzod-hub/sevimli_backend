package coder.uz.uzum.repository;

import coder.uz.uzum.entity.OrderNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderNotificationRepository extends JpaRepository<OrderNotification, UUID> {
    List<OrderNotification> findByUserIdOrderByCreatedAtDesc(UUID userId);
}
