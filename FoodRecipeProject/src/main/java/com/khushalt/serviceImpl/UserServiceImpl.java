package com.khushalt.serviceImpl;

import com.khushalt.config.JWTProvider;
import com.khushalt.model.User;
import com.khushalt.repository.UserRepository;
import com.khushalt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JWTProvider jwtProvider;
    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user=userRepository.findById(userId);
        return user.orElseThrow(() -> new Exception("User not found with id " + userId));
    }

    @Override
    public User findUserByJwt(String Jwt) throws Exception {
       String email=jwtProvider.getEmailFromJwtToken(Jwt);
       if(email==null){
           throw new  Exception("Provide a valid Jwt token");
       }
       User user=userRepository.findByEmail(email);
       if(user==null){
           throw new Exception("User not found with email");
       }
        return user;
    }
}
