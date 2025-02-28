package com.example.ShoppingCart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.User;
import com.example.ShoppingCart.dto.LoginRequest;
import com.example.ShoppingCart.exception.InvalidCredentialsException;
import com.example.ShoppingCart.exception.UserNotFoundException;
import com.example.ShoppingCart.repo.LoginRepo;
import com.example.ShoppingCart.util.JWTUtil;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtil jwtUtil;

	@Override
	public ResponseEntity<ResponseStructure<String>> loginUser(LoginRequest loginRequest) {
		User u=loginRepo.findByEmail(loginRequest.getEmail());
		
		if(u==null) {
			throw new UserNotFoundException("User with this email does not exist!");
		}
		
		if(isAuthenticated(loginRequest)) {
			User user=loginRepo.findByEmail(loginRequest.getEmail());
			String token=jwtUtil.generateToken(user);
			ResponseStructure<String> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("User loged in Successfully!!!");
			responseStructure.setToken(token);
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Inavlid username or password!!");
	}
	
	public boolean isAuthenticated(LoginRequest loginRequest) {
		String email=loginRequest.getEmail();
		User user=loginRepo.findByEmail(email);
		if(user!=null) {
			String password=loginRequest.getPassword();
			String encodePassword=user.getPassword();
			if(passwordEncoder.matches(password,encodePassword)) {
				Optional<User> u1=loginRepo.findOneByEmailAndPassword(email, encodePassword);
				if(u1.isPresent()) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}

}
