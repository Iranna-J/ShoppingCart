package com.example.ShoppingCart.service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ShoppingCart.config.AuthResponse;
import com.example.ShoppingCart.config.PasswordValidator;
import com.example.ShoppingCart.config.ResponseStructure;
import com.example.ShoppingCart.domain.Role;
import com.example.ShoppingCart.domain.User;
import com.example.ShoppingCart.exception.DuplicateEmailException;
import com.example.ShoppingCart.exception.InvalidMobileNumberException;
import com.example.ShoppingCart.exception.InvalidPasswordException;
import com.example.ShoppingCart.exception.UserNotFoundByIdException;
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
	public ResponseEntity<AuthResponse<User>> saveUser(User user) {
		EnumSet<Role> validRoles = EnumSet.of(Role.ADMIN, Role.CUSTOMER, Role.SELLER);

		if (!validRoles.contains(user.getRole())) {
			throw new UserRoleNotMatchException("Invalid User Role!!!");
		}

		if (!PasswordValidator.isValidPassword(user.getPassword())) {
			throw new InvalidPasswordException(
					"Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one special character, and one number.");
		}

		if (userRepo.existsByEmail(user.getEmail())) {
			throw new DuplicateEmailException("Email is already registered. Please use a different email.");
		}

		if (!isValidMobileNumber(user.getMobile())) {
			throw new InvalidMobileNumberException("Mobile number must contain exactly 10 digits.");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User u = userRepo.save(user);
		String token = jwtUtil.generateToken(user);

		AuthResponse<User> responseStructure = new AuthResponse<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User Created Successfully!!!");
		responseStructure.setToken(token);
		responseStructure.setData(u);

		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public boolean isValidMobileNumber(long mobile) {
		String mobileStr = String.valueOf(mobile);
		return mobileStr.matches("\\d{10}"); // Ensures exactly 10 digits
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> getUser(long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundByIdException("User doesn't exist!!");
		}
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("User Found Successfully!!!");
		responseStructure.setData(user);

		return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findAllUsers() {
		List<User> userList = new ArrayList<>();
		userList = userRepo.findAll();
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("All users found successfully!!!");
		responseStructure.setData(userList);

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<User>> updateUser(long id, User user) {
	    Optional<User> existingUserOptional = userRepo.findById(id);
	    
	    if (existingUserOptional.isEmpty()) {
	        throw new UserNotFoundByIdException("User doesn't exist!!!");
	    }

	    User existingUser = existingUserOptional.get();

	    Optional<User> userWithSameEmail = userRepo.findByEmail(user.getEmail());
	    if (userWithSameEmail.isPresent() && userWithSameEmail.get().getId() != id) {
	        throw new DuplicateEmailException("Email is already registered. Please use a different email.");
	    }

	    EnumSet<Role> validRoles = EnumSet.of(Role.ADMIN, Role.CUSTOMER, Role.SELLER);
	    if (!validRoles.contains(user.getRole())) {
	        throw new UserRoleNotMatchException("Invalid User Role!!!");
	    }

	    if (!PasswordValidator.isValidPassword(user.getPassword())) {
	        throw new InvalidPasswordException(
	                "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one special character, and one number.");
	    }

	    if (!isValidMobileNumber(user.getMobile())) {
	        throw new InvalidMobileNumberException("Mobile number must contain exactly 10 digits.");
	    }

	    user.setId(id);
	    user.setPassword(passwordEncoder.encode(user.getPassword()));

	    User updatedUser = userRepo.save(user);

	    ResponseStructure<User> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatus(HttpStatus.OK.value());
	    responseStructure.setMessage("User updated successfully!!!");
	    responseStructure.setData(updatedUser);

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> deleteUser(long id) {
		Optional<User> user=userRepo.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundByIdException("User Doen't exist!!!");
		}
		userRepo.delete(user.get());
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("User Deleted Successfully!!!");
		responseStructure.setData(user.get());
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

}
