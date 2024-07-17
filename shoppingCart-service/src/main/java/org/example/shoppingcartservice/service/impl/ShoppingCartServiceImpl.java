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
        Integer newStock = productDTO.getStock() - quantity;

        if (newStock < 0) {
            System.out.println("There is not enough stock.");
            return null;
        } else {

            ShoppingCart shoppingCart = new ShoppingCart();

            productsAPI.changeProductStock(productCode, newStock);

            shoppingCart.getProducts().add(productDTO);
            shoppingCart.setTotalPrice((shoppingCart.getTotalPrice()) +
                    (productDTO.getProductPrice() * quantity));

            return shoppingCartRepository.save(shoppingCart);
        }
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
    public void addProductToShoppingCart(Long productCode, Long id_shoppingCart, Integer quantity) {

        ProductDTO productDTO = productsAPI.getProductByCode(productCode);
        productDTO.setQuantity(quantity);
        Integer newStock = productDTO.getStock() - quantity;

        if (newStock < 0) {
            System.out.println("There is not enough stock.");
        } else {

            ShoppingCart shoppingCart = shoppingCartRepository.findById(id_shoppingCart).orElse(null);

            productsAPI.changeProductStock(productCode, newStock);

            shoppingCart.getProducts().add(productDTO);
            shoppingCart.setTotalPrice((shoppingCart.getTotalPrice()) +
                    (productDTO.getProductPrice() * quantity));

            shoppingCartRepository.save(shoppingCart);
        }
    }


    /*
    public void deleteProductByCode(Long productCode) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductCode().equals(productCode)) {
                products.remove(i);
                break;  // Salir del bucle una vez que se encuentra y elimina el producto
            }
        }
    }
     */
    @Override
    public void removeProductFromShoppingCart(Long productCode, Long id_shoppingCart, Integer quantity) {

        ProductDTO productDTO = productsAPI.getProductByCode(productCode);
        productDTO.setQuantity(quantity);
        Integer newStock = productDTO.getStock() - quantity;

        if (newStock < 0) {
            System.out.println("There is not enough stock.");
        } else {

            ShoppingCart shoppingCart = shoppingCartRepository.findById(id_shoppingCart).orElse(null);
            List<ProductDTO> products = shoppingCart.getProducts();

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductCode().equals(productCode)) {
                    shoppingCart.setTotalPrice((shoppingCart.getTotalPrice()) -
                            (products.get(i).getProductPrice() * products.get(i).getQuantity()));

                    products.remove(i);
                }
            }


            productsAPI.changeProductStock(productCode, newStock);

            shoppingCart.getProducts().add(productDTO);
            shoppingCart.setTotalPrice((shoppingCart.getTotalPrice()) +
                    (productDTO.getProductPrice() * quantity));

            shoppingCartRepository.save(shoppingCart);
        }


    }

    @Override
    public void deleteShoppingCart(Long id_shoppingCart) {

    }
}
