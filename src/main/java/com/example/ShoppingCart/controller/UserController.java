package com.example.ShoppingCart.controller;

import java.awt.desktop.UserSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ShoppingCart.config.AuthResponse;
import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.User;
import com.example.ShoppingCart.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
//	@PostMapping("/signup")
//	public ResponseEntity<AuthResponse<User>> saveUser(@RequestBody User user) {
//		return userService.saveUser(user);
//	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<User>> getUser(@RequestParam long id){
		return userService.getUser(id);
	}
	
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<User>> findAllUsers(){
		return userService.findAllUsers();
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestParam long id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam long id){
		return userService.deleteUser(id);
	}
}
