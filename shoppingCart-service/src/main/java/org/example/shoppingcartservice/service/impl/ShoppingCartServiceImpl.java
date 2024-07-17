package org.example.shoppingcartservice.service.impl;

import org.example.shoppingcartservice.dto.ProductDTO;
import org.example.shoppingcartservice.model.ShoppingCart;
import org.example.shoppingcartservice.repository.IProductsAPI;
import org.example.shoppingcartservice.repository.ShoppingCartRepository;
import org.example.shoppingcartservice.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {


    @Autowired
    private IProductsAPI productsAPI;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart createShoppingCart(Long productCode, Integer quantity) {

        ProductDTO productDTO = productsAPI.getProductByCode(productCode);
        productDTO.setQuantity(quantity);



    }

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return null;
    }

    @Override
    public ShoppingCart findShoppingCartById(Long id_shoppingCart) {
        return null;
    }

    @Override
    public void addProductToShoppingCart(Long productCode, Integer quantity) {

    }

    @Override
    public void removeProductFromShoppingCart(Long productCode, Integer quantity) {

    }

    @Override
    public void deleteShoppingCart(Long id_shoppingCart) {

    }
}
