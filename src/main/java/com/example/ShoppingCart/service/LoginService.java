package com.example.ShoppingCart.service;

import org.springframework.http.ResponseEntity;

import com.example.ShoppingCart.config.AuthResponse;
import com.example.ShoppingCart.dto.LoginRequest;

public interface LoginService {

	ResponseEntity<AuthResponse<String>> loginUser(LoginRequest loginRequest);
}
