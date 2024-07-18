package org.example.shoppingcartservice.service;

import org.example.shoppingcartservice.model.ShoppingCart;

import java.util.List;

public interface IShoppingCartService {

    ShoppingCart createShoppingCart(Long productCode, Integer quantity);
    List<ShoppingCart> getAllShoppingCarts();
    ShoppingCart findShoppingCartById(Long id_shoppingCart);
    void addProductToShoppingCart(Long productCode, Long id_shoppingCart, Integer quantity);
    void removeProductFromShoppingCart(Long productCode, Long id_shoppingCart);
    void deleteShoppingCart(Long id_shoppingCart);

}
