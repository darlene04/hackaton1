package com.example.hackaton1.controller;

import com.example.hackaton1.model.entity.User;
import com.example.hackaton1.model.entity.UserLimit;
import com.example.hackaton1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/{id}/limits")
    public ResponseEntity<UserLimit> addUserLimit(@PathVariable Long id, @RequestBody UserLimit limit) {
        UserLimit savedLimit = userService.addUserLimit(id, limit);
        return ResponseEntity.ok(savedLimit);
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<Object> getUserConsumption(@PathVariable Long id) {
        Object consumption = userService.getUserConsumption(id);
        return ResponseEntity.ok(consumption);
    }
}