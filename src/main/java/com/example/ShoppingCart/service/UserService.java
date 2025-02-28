package com.example.ShoppingCart.service;

import org.springframework.http.ResponseEntity;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.User;

public interface UserService {
	ResponseEntity<ResponseStructure<User>> saveUser(User user);
}
