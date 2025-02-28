package com.example.ShoppingCart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.dto.LoginRequest;
import com.example.ShoppingCart.service.LoginService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<String>> loginUser(@RequestBody LoginRequest loginRequest){
		return loginService.loginUser(loginRequest);
	}
}
