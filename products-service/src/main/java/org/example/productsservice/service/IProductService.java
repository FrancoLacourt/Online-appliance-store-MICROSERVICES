package org.example.productsservice.service;

import org.example.productsservice.model.Product;

import java.util.List;

public interface IProductService {

    Product createProduct(String productName, String brand, Integer productPrice, Integer stock);
    List<Product> getAllProducts();
    Product findProductByCode(Long productCode);
    void changeProductPrice(Long productCode, Integer productPrice);
    void changeProductStock(Long productCode, Integer quantity);
    void deleteProduct(Long productCode);

}
