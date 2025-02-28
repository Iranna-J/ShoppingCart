package com.example.ShoppingCart.service;

import org.springframework.http.ResponseEntity;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.dto.LoginRequest;

public interface LoginService {

	ResponseEntity<ResponseStructure<String>> loginUser(LoginRequest loginRequest);
}
