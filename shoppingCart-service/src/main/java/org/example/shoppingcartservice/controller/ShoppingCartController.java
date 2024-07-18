package org.example.shoppingcartservice.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.ws.rs.Path;
import org.example.shoppingcartservice.dto.ProductDTO;
import org.example.shoppingcartservice.model.ShoppingCart;
import org.example.shoppingcartservice.repository.IProductsAPI;
import org.example.shoppingcartservice.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService shoppingCartService;
    @Autowired
    private IProductsAPI productsAPI;


    @PostMapping("/create/{productCode}")
    public ResponseEntity<ShoppingCart> createShoppingCart (@PathVariable Long productCode, @RequestParam Integer quantity) {

        ShoppingCart shoppingCart = shoppingCartService.createShoppingCart(productCode, quantity);

        if (productCode == null || quantity == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        if (shoppingCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCart);
        }
    }

    @GetMapping("/listOfShoppingCarts")
    public ResponseEntity<List<ShoppingCart>> getShoppingCarts() {

        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllShoppingCarts();

        if (shoppingCarts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCarts);
        }
    }

    @GetMapping("/getShoppingCartById/{id_shoppingCart}")
    public ResponseEntity<ShoppingCart> getShoppingCartById (@PathVariable Long id_shoppingCart) {

        ShoppingCart shoppingCart = shoppingCartService.findShoppingCartById(id_shoppingCart);

        if (shoppingCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
        }
    }

    @PutMapping("/addProductToShoppingCart/{productCode}/{id_shoppingCart}")
    public ResponseEntity<ShoppingCart> addProductToShoppingCart (@PathVariable Long productCode,
                                                                  @PathVariable Long id_shoppingCart,
                                                                  @RequestParam Integer quantity) {

        ShoppingCart shoppingCart = shoppingCartService.findShoppingCartById(id_shoppingCart);
        ProductDTO productDTO = productsAPI.getProductByCode(productCode);

        if (shoppingCart == null || productDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            shoppingCartService.addProductToShoppingCart(productCode, id_shoppingCart, quantity);
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
        }
    }

    @PutMapping("/removeProductFromShoppingCart/{productCode}/{id_shoppingCart}")
    public ResponseEntity<ShoppingCart> removeProductFromShoppingCart (@PathVariable Long productCode,
                                                                       @PathVariable Long id_shoppingCart) {

        ShoppingCart shoppingCart = shoppingCartService.findShoppingCartById(id_shoppingCart);
        ProductDTO productDTO = productsAPI.getProductByCode(productCode);

        if (shoppingCart == null || productDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            shoppingCartService.removeProductFromShoppingCart(productCode, id_shoppingCart);
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
        }
    }

    @DeleteMapping("/deleteShoppingCartById/{id_shoppingCart}")
    public ResponseEntity<ShoppingCart> deleteShoppingCart (@PathVariable Long id_shoppingCart) {

        ShoppingCart shoppingCart = shoppingCartService.findShoppingCartById(id_shoppingCart);

        if (shoppingCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            shoppingCartService.deleteShoppingCart(id_shoppingCart);
            return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
        }
    }
}
