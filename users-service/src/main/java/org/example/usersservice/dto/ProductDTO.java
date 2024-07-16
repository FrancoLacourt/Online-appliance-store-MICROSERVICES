package org.example.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productCode;
    private String productName;
    private String brand;
    private Integer productPrice;
    private Integer stock;
}
