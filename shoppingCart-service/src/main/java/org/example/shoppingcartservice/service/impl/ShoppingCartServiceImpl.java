package org.example.shoppingcartservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.example.shoppingcartservice.dto.ProductDTO;
import org.example.shoppingcartservice.dto.ProductStockDTO;
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
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackCreateShoppingCart")
    @Retry(name = "products-service")
    public ShoppingCart createShoppingCart() {

            ShoppingCart shoppingCart = new ShoppingCart();
            return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {

        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart findShoppingCartById(Long id_shoppingCart) {

        return shoppingCartRepository.findById(id_shoppingCart).orElse(null);
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackAddProductToShoppingCart")
    @Retry(name = "products-service")
    public void addProductToShoppingCart(Long productCode, Long id_shoppingCart, Integer quantity) {

        ProductStockDTO productStockDTO = productsAPI.getStockByCode(productCode);
        Integer newStock = productStockDTO.getStock() - quantity;
        Integer totalPrice = 0;
        Integer counter = 0;

        if (newStock < 0) {
            System.out.println("There is not enough stock.");
        } else {

            ShoppingCart shoppingCart = shoppingCartRepository.findById(id_shoppingCart).orElse(null);
            List<ProductDTO> products = shoppingCart.getProducts();

            for (ProductDTO product : products) {
                if (product.getProductCode().equals(productCode)) {
                    product.setQuantity((product.getQuantity()) + quantity);
                    productsAPI.changeProductStock(productCode, newStock);
                    counter ++;
                    break;
                }
            }

            if (counter == 0) {
                ProductDTO productDTO = productsAPI.getProductByCode(productCode);
                productDTO.setQuantity(quantity);
                productsAPI.changeProductStock(productCode, newStock);
                products.add(productDTO);
            }

            for (ProductDTO product : products) {
                totalPrice = totalPrice + (product.getQuantity() * product.getProductPrice());
            }

            shoppingCart.setTotalPrice(totalPrice);
            shoppingCartRepository.save(shoppingCart);
        }
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackRemoveProductFromShoppingCart")
    @Retry(name = "products-service")
    public void removeProductFromShoppingCart(Long productCode, Long id_shoppingCart) {

            ShoppingCart shoppingCart = shoppingCartRepository.findById(id_shoppingCart).orElse(null);
            List<ProductDTO> products = shoppingCart.getProducts();
            Integer totalPrice = 0;

            for (ProductDTO product : products) {
                if (product.getProductCode().equals(productCode)) {
                    ProductStockDTO productStockDTO = productsAPI.getStockByCode(productCode);

                    Integer newStock = productStockDTO.getStock() + product.getQuantity();
                    productsAPI.changeProductStock(productCode, newStock);
                    products.remove(product);
                    break;
                }
            }

            for (ProductDTO product : products) {
                totalPrice = totalPrice + (product.getQuantity() * product.getProductPrice());
            }

            shoppingCart.setTotalPrice(totalPrice);
            shoppingCartRepository.save(shoppingCart);

        }

    @Override
    public void deleteShoppingCart(Long id_shoppingCart) {

        ShoppingCart shoppingCart = shoppingCartRepository.findById(id_shoppingCart).orElse(null);

        shoppingCart.getProducts().clear();

        shoppingCartRepository.deleteById(id_shoppingCart);
    }

    public ShoppingCart fallbackCreateShoppingCart (Long productCode, Integer quantity, Throwable throwable) {

        return new ShoppingCart(99999999L, 999999, null);
    }

    public void fallbackAddProductToShoppingCart(Long productCode, Long id_shoppingCart, Integer quantity, Throwable throwable) {
        System.out.println("Fallback method for addProductToShoppingCart called.");
    }

    public void fallbackRemoveProductFromShoppingCart(Long productCode, Long id_shoppingCart, Throwable throwable) {
        System.out.println("Fallback method for removeProductFromShoppingCart called.");
    }
}