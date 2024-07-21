package org.example.usersservice.service;

import org.example.usersservice.model.User;

import java.util.List;

public interface IUserService {

    User createUser(String name, String lastname);
    List<User> getAllUsers();
    User getUserById(Long id_user);
    void deleteUserById(Long id_user);

}
