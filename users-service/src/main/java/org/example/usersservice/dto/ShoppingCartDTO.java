package org.example.usersservice.dto;

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
public class ShoppingCartDTO {

    private Long id_shoppingCart;
    private Integer totalPrice;
    private List<ProductDTO> products;
}
