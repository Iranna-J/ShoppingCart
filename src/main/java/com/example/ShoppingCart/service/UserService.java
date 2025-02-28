package com.example.ShoppingCart.service;

import org.springframework.http.ResponseEntity;

import com.example.ShoppingCart.config.AuthResponse;
import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.User;

public interface UserService {
	ResponseEntity<AuthResponse<User>> saveUser(User user);

	ResponseEntity<ResponseStructure<User>> getUser(long id);

	ResponseEntity<ResponseStructure<User>> findAllUsers();

	ResponseEntity<ResponseStructure<User>> updateUser(long id, User user);

	ResponseEntity<ResponseStructure<User>> deleteUser(long id);
}
