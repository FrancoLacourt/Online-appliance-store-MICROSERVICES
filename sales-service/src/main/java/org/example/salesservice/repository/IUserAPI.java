package org.example.salesservice.repository;

import org.example.salesservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "users-service")
public interface IUserAPI {

    @GetMapping("user/getUserById/{id_user}")
    public UserDTO getUserById (@PathVariable Long id_user);

    @PutMapping("user/cleanShoppingCart/{id_user}")
    public void cleanShoppingCart (@PathVariable Long id_user);

}
