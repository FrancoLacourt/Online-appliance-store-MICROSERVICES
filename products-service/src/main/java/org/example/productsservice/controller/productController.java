package org.example.productsservice.controller;

import org.example.productsservice.model.Product;
import org.example.productsservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestParam String productName,
                                                 @RequestParam String brand,
                                                 @RequestParam Integer productPrice,
                                                 @RequestParam Integer stock) {

        if (productName == null || brand == null || productPrice == null || stock == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {

            Product product = productService.createProduct(productName, brand, productPrice, stock);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }
    }

    @GetMapping("/listOfProducts")
    public ResponseEntity<List<Product>> getProducts() {

        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
    }

    @GetMapping("/getProductByCode/{productCode}")
    public ResponseEntity<Product> getProductByCode (@PathVariable Long productCode) {

        Product product = productService.findProductByCode(productCode);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
