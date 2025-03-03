package com.example.ShoppingCart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ShoppingCart.config.AuthResponse;
import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.User;
import com.example.ShoppingCart.dto.LoginRequest;
import com.example.ShoppingCart.service.LoginService;
import com.example.ShoppingCart.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping
	public ResponseEntity<AuthResponse<String>> loginUser(@RequestBody LoginRequest loginRequest){
		return loginService.loginUser(loginRequest);
	}
}
