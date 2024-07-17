package org.example.shoppingcartservice.repository;

import org.example.shoppingcartservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "products-service")
public interface IProductsAPI {

    @GetMapping("products/getProductByCode/{productCode}")
    public ProductDTO getProductByCode (@PathVariable Long productCode);

    @PutMapping("products/changeProductStock/{productCode}")
    public void changeProductStock (@PathVariable Long productCode, @RequestParam
    Integer quantity);

}
