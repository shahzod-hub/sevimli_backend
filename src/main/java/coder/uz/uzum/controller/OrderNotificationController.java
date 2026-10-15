package coder.uz.uzum.controller;

import coder.uz.uzum.entity.OrderNotification;
import coder.uz.uzum.service.OrderNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class OrderNotificationController {
    private final OrderNotificationService notificationService;

    @GetMapping("/user/{userId}")
    public List<OrderNotification> getByUserId(@PathVariable UUID userId) {
        return notificationService.getByUserId(userId);
    }

    @PutMapping("/{id}/read")
    public boolean markAsRead(@PathVariable UUID id) {
        return notificationService.markAsRead(id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable UUID id) {
        return notificationService.delete(id);
    }
}
