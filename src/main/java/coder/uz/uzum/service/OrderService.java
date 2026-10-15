package coder.uz.uzum.service;

import coder.uz.uzum.entity.Order;
import coder.uz.uzum.repository.OrderRepository;
import coder.uz.uzum.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService  {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderNotificationService orderNotificationService;

    @Transactional
    public boolean create(Order order) {
        if (order == null || order.getProductId() == null) {
            return false;
        }

        if (productRepository.decrementQuantityIfAvailable(order.getProductId()) == 0) {
            return false;
        }

        orderRepository.save(order);
        return true;
    }


    public Order getById(UUID id) {
        if (id == null) {
            return null;
        } else {
          return   orderRepository.getById(id);

        }
    }


    @Transactional
    public boolean update(UUID id, Order order) {
        if (id==null||order==null){
            return false;
        }else {
            Order old_order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order topilmadi"));
            String previousStatus = old_order.getStatus();
            old_order.setUserId(order.getUserId());
            old_order.setProductId(order.getProductId());
            old_order.setDeliveryAddress(order.getDeliveryAddress());
            old_order.setDeliveryLatitude(order.getDeliveryLatitude());
            old_order.setDeliveryLongitude(order.getDeliveryLongitude());
            old_order.setStatus(order.getStatus());

            orderRepository.save(old_order);
            if (!java.util.Objects.equals(previousStatus, old_order.getStatus())) {
                orderNotificationService.notifyOrderStatusChanged(
                        old_order.getUserId(),
                        old_order.getId(),
                        old_order.getStatus()
                );
            }
            return true;
        }
    }
    public boolean delete(UUID id) {
      if (id==null){
          return false;
      }else{
          orderRepository.deleteById(id);
          return true;
      }
    }


    public List getAll() {
        return orderRepository.findAll();
    }
}
