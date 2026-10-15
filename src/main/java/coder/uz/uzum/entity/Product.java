package coder.uz.uzum.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name="products")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;
    private String name;
    private Double price;
    private int quantity;
    private String image;
    private LocalDateTime createDate;



    public Product( String name,  Double price, int quantity,String image,LocalDateTime createDate) {

        this.name = name;

        this.price = price;
        this.quantity = quantity;
        this.image=image;
        this.createDate=createDate;
    }





}
