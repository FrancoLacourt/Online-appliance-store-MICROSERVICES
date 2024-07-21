package org.example.usersservice.controller;

import org.example.usersservice.model.User;
import org.example.usersservice.repository.IShoppingCartAPI;
import org.example.usersservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser (@RequestParam String name, @RequestParam String lastname) {

        User user = userService.createUser(name, lastname);

        if (name == null || lastname == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }

    @GetMapping("/listOfUsers")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
    }

    @GetMapping("/getUserById/{id_user}")
    public ResponseEntity<User> getUserById (@PathVariable Long id_user) {

        User user = userService.getUserById(id_user);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @GetMapping("/deleteUserById/{id_user}")
    public ResponseEntity<User> deleteUser (@PathVariable Long id_user) {

        User user = userService.getUserById(id_user);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            userService.deleteUserById(id_user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }
}
