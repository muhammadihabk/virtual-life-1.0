package com.example.virtual_life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.virtual_life.entity.User;
import com.example.virtual_life.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/app/user")
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<Object[]> findAll() {
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public List<Object[]> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping
    public void updateUser(@RequestBody ObjectNode user) {
        Long id = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;
        if(user.get("id") != null) {
            id = user.get("id").asLong();
        }
        if(user.get("firstName") != null) {
            firstName = user.get("firstName").asText();
        }
        if(user.get("lastName") != null) {
            lastName = user.get("lastName").asText();
        }
        if(user.get("email") != null) {
            email = user.get("email").asText();
        }
        if(user.get("password") != null) {
            password = user.get("password").asText();
        }
        userService.updateUser(id, firstName, lastName, email, password);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
