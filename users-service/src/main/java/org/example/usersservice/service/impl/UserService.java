package org.example.usersservice.service.impl;

import org.example.usersservice.dto.ShoppingCartDTO;
import org.example.usersservice.model.User;
import org.example.usersservice.repository.IShoppingCartAPI;
import org.example.usersservice.repository.UserRepository;
import org.example.usersservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IShoppingCartAPI shoppingCartAPI;

    @Override
    public User createUser(String name, String lastname) {

        User user = new User(name, lastname);

        ShoppingCartDTO shoppingCartDTO = shoppingCartAPI.createShoppingCart();
        user.setShoppingCart(shoppingCartDTO);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        for (User user : users) {
            user.setShoppingCart(shoppingCartAPI.getShoppingCartById
                    (user.getShoppingCart().getId_shoppingCart()));
        }
        return users;
    }

    @Override
    public User getUserById(Long id_user) {

        User user = userRepository.findById(id_user).orElse(null);

        user.setShoppingCart(shoppingCartAPI.getShoppingCartById
                (user.getShoppingCart().getId_shoppingCart()));

        return user;
    }

    @Override
    public void deleteUserById(Long id_user) {

        User user = userRepository.findById(id_user).orElse(null);
        ShoppingCartDTO shoppingCartDTO = user.getShoppingCart();
        shoppingCartDTO.getProducts().clear();
        user.setShoppingCart(null);

        userRepository.deleteById(id_user);
    }
}
