package org.example.usersservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.usersservice.dto.ShoppingCartDTO;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    private String name;
    private String lastname;
    private ShoppingCartDTO shoppingCart;

    public User(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }
}
