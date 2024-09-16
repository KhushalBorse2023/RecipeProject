package com.khushalt.service;

import com.khushalt.model.User;

public interface UserService {
public User findUserById(Long userId) throws Exception;
public User findUserByJwt(String Jwt) throws Exception;

}
