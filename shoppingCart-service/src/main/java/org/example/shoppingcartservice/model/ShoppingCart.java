package org.example.shoppingcartservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.shoppingcartservice.dto.ProductDTO;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_shoppingCart;
    private Integer totalPrice;

    @ElementCollection
    private List<ProductDTO> products;
}
