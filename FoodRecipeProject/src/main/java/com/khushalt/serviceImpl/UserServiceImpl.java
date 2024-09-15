package com.khushalt.serviceImpl;

import com.khushalt.model.User;
import com.khushalt.repository.UserRepository;
import com.khushalt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user=userRepository.findById(userId);
        return user.orElseThrow(() -> new Exception("User not found with id " + userId));
    }
}
