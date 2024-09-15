package com.khushalt.controller;

import com.khushalt.model.User;
import com.khushalt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception {
        User isExist = userRepository.findByEmail(user.getEmail());
        if (isExist != null) {
            throw new Exception("User already exist with Email " + user.getEmail());
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception {
        userRepository.deleteById(userId);
        return "User is deleted Successfully";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() throws Exception {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found with email" + email);
        }
        return user;
    }
}


