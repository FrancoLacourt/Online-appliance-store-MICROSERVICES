package org.example.usersservice.repository;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "shoppingCart-service")
public interface IShoppingCartAPI {
}
