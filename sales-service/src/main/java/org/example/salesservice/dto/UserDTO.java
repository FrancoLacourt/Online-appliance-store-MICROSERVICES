package org.example.salesservice.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserDTO {

    private Long id_user;
    private String name;
    private String lastname;
    private ShoppingCartDTO shoppingCart;

}
