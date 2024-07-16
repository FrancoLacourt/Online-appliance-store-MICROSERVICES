package org.example.shoppingcartservice.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductDTO {

    private Long productCode;
    private String productName;
    private String brand;
    private Integer productPrice;
}
