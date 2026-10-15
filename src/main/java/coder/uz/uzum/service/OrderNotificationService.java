package coder.uz.uzum.service;

import coder.uz.uzum.entity.OrderNotification;
import coder.uz.uzum.repository.OrderNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderNotificationService {
    private final OrderNotificationRepository notificationRepository;

    public void notifyOrderStatusChanged(UUID userId, UUID orderId, String status) {
        if (userId == null) return;

        OrderNotification notification = new OrderNotification();
        notification.setUserId(userId);
        notification.setTitle("Buyurtma holati yangilandi");
        notification.setMessage("Buyurtma #" + orderId + " holati: " + statusLabel(status) + ".");
        notification.setType("info");
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    public List<OrderNotification> getByUserId(UUID userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public boolean markAsRead(UUID notificationId) {
        return notificationRepository.findById(notificationId)
                .map(notification -> {
                    notification.setRead(true);
                    notificationRepository.save(notification);
                    return true;
                })
                .orElse(false);
    }

    public boolean delete(UUID notificationId) {
        if (!notificationRepository.existsById(notificationId)) return false;
        notificationRepository.deleteById(notificationId);
        return true;
    }

    private String statusLabel(String status) {
        if (status == null) return "Yangi";
        return switch (status) {
            case "CONFIRMED" -> "Tasdiqlandi";
            case "PROCESSING" -> "Tayyorlanmoqda";
            case "SHIPPED" -> "Yo‘lda";
            case "DELIVERED" -> "Yetkazildi";
            case "CANCELLED" -> "Bekor qilindi";
            default -> "Yangi";
        };
    }
}
