package coder.uz.uzum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name="orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;
   private UUID userId;
   private UUID productId;
   private String deliveryAddress;
   private Double deliveryLatitude;
   private Double deliveryLongitude;
    private String status = "NEW";
   @Column(updatable = false)
   private LocalDateTime localDateTime;


}
