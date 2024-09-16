package com.khushalt.controller;

import com.khushalt.model.User;
import com.khushalt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/api/user/profile")
    public User findUserByJwt(@RequestHeader ("Authorization") String Jwt) throws  Exception{
        User user=userService.findUserByJwt(Jwt);
        return user;
    }
}


