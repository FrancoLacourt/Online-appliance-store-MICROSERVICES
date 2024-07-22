package org.example.usersservice.repository;

import org.example.usersservice.dto.ShoppingCartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "shoppingCart-service")
public interface IShoppingCartAPI {

    @PostMapping("shoppingCart/create")
    public ShoppingCartDTO createShoppingCart ();

    @GetMapping("shoppingCart/getShoppingCartById/{id_shoppingCart}")
    public ShoppingCartDTO getShoppingCartById (@PathVariable Long id_shoppingCart);

    @PutMapping("shoppingCart/removeAllProducts/{id_shoppingCart}")
    public void removeAllProducts (@PathVariable Long id_shoppingCart);


}
