package coder.uz.uzum.controller;
import coder.uz.uzum.entity.Order;
import coder.uz.uzum.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/create")
    public boolean create(@RequestBody Order order){
    order.setLocalDateTime(LocalDateTime.now());
       return orderService.create(order);
    }
   @GetMapping("/getById/{id}")
    public Order getById(@PathVariable UUID id){
        return orderService.getById(id);
   }
   @PutMapping("/update/{id}")
    public boolean update(
            @PathVariable UUID id,
            @RequestBody Order order){
        return orderService.update(id,order);
   }
   @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable UUID id){
        return orderService.delete(id);

   }
    @GetMapping("/getAll")
    public List getAll() {
        return orderService.getAll();

   }



}
