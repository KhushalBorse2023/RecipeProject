package com.khushalt.controller;

import com.khushalt.config.JWTProvider;
import com.khushalt.model.User;
import com.khushalt.repository.UserRepository;
import com.khushalt.request.LoginRequest;
import com.khushalt.response.AuthResponse;
import com.khushalt.serviceImpl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        User isExistEmail = userRepository.findByEmail(email);
        if (isExistEmail != null) {
            throw new Exception("Email is already used with another account");
        }
        User createdUser = User.builder().
                email(user.getEmail()).
                fullName(user.getFullName()).
                password(passwordEncoder.encode(user.getPassword())).
                build();
        User savedUser = userRepository.save(createdUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse();
        response.setJwt(token);
        response.setMessage("SignUp Success");
        return response;
    }

    @PostMapping("/signin")
    public AuthResponse signInHandler(@RequestBody LoginRequest loginRequest) throws Exception {
        String userName = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Authentication authentication = authenticate(userName, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse();
        response.setJwt(token);
        response.setMessage("SignUp Success");
        return response;
    }

    private Authentication authenticate(String userName, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
        if (userDetails == null) {
            throw new BadCredentialsException("User not found");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }
}