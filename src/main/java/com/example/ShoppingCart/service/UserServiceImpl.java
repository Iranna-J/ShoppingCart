package com.example.ShoppingCart.service;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ShoppingCart.config.PasswordValidator;
import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.Role;
import com.example.ShoppingCart.domain.User;
import com.example.ShoppingCart.exception.DuplicateEmailException;
import com.example.ShoppingCart.exception.InvalidMobileNumberException;
import com.example.ShoppingCart.exception.InvalidPasswordException;
import com.example.ShoppingCart.exception.UserRoleNotMatchException;
import com.example.ShoppingCart.repo.UserRepo;
import com.example.ShoppingCart.util.JWTUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		EnumSet<Role> validRoles=EnumSet.of(Role.ADMIN, Role.CUSTOMER, Role.SELLER);
		
	    if (!validRoles.contains(user.getRole())) {
	        throw new UserRoleNotMatchException("Invalid User Role!!!");
	    }
	    
	    if (!PasswordValidator.isValidPassword(user.getPassword())) {
            throw new InvalidPasswordException("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one special character, and one number.");
        }
	    
	    if (userRepo.existsByEmail(user.getEmail())) {
	        throw new DuplicateEmailException("Email is already registered. Please use a different email.");
	    }
	    
	    if (!isValidMobileNumber(user.getMobile())) {
	        throw new InvalidMobileNumberException("Mobile number must contain exactly 10 digits.");
	    }

	    
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    User u = userRepo.save(user);
	    String token=jwtUtil.generateToken(user);

	    ResponseStructure<User> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatus(HttpStatus.CREATED.value());
	    responseStructure.setMessage("User Created Successfully!!!");
	    responseStructure.setToken(token);
	    responseStructure.setData(u);

	    return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}
	
	public boolean isValidMobileNumber(long mobile) {
	    String mobileStr = String.valueOf(mobile);
	    return mobileStr.matches("\\d{10}");  // Ensures exactly 10 digits
	}

}
