package org.example.productsservice.service.impl;

import org.example.productsservice.model.Product;
import org.example.productsservice.repository.ProductRepository;
import org.example.productsservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(String productName, String brand, Integer productPrice, Integer stock) {

        Product product = new Product(productName, brand, productPrice, stock);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product findProductByCode(Long productCode) {

        return productRepository.findById(productCode).orElse(null);
    }

    @Override
    public void changeProductPrice(Long productCode, Integer productPrice) {

        Product product = productRepository.findById(productCode).orElse(null);
        product.setProductPrice(productPrice);
    }

    @Override
    public void changeProductStock(Long productCode, Integer stock) {

        Product product = productRepository.findById(productCode).orElse(null);
        product.setStock(stock);
    }

    @Override
    public void deleteProduct(Long productCode) {

        productRepository.deleteById(productCode);
    }
}
