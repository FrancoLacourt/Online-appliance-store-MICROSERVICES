package org.example.shoppingcartservice.repository;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "products-service")
public interface IProductsAPI {
}
