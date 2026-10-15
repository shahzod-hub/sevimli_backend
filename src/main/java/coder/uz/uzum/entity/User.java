package coder.uz.uzum.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
 @Entity
@Table(name="users")
 @Getter
 @Setter
 @ToString
@AllArgsConstructor
 @NoArgsConstructor
public  class User {
     @Id
@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private LocalDateTime createDate;
    @Enumerated(EnumType.STRING)
    private Role role;



     public User(String name, String email, String password, LocalDateTime createDate) {
         this.name = name;
         this.email = email;
         this.password = password;

         this.createDate = createDate;
     }
 }

