package com.example.ShoppingCart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ShoppingCart.config.ResponseStructure;

@RestControllerAdvice
public class ApplicationHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidEnum(IllegalArgumentException ex) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData(null);

		return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(notFoundByIdException.class)
	public ResponseEntity<ResponseStructure<String>> userNotFoundById(notFoundByIdException ex){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData(null);
		
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidPasswordException(InvalidPasswordException ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ResponseStructure<String>> handleDuplicateEmailException(DuplicateEmailException ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidMobileNumberException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidMobileNumberException(
			InvalidMobileNumberException ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserNotFoundException(UserNotFoundException ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setMessage(ex.getMessage());
		response.setData(null);

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(ProductAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> handleDuplicateProducts(ProductAlreadyExistException ex){
		ResponseStructure<String> response=new ResponseStructure<>();
		response.setStatus(HttpStatus.CONFLICT.value());
		response.setMessage(ex.getMessage());
		response.setData(null);
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<ResponseStructure<String>> handleDuplicateProducts(InsufficientStockException ex){
		ResponseStructure<String> response=new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		response.setData(null);
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderStatusNotMatchException.class)
	public ResponseEntity<ResponseStructure<String>> orderStatusNotMatch(OrderStatusNotMatchException ex){
		ResponseStructure<String> response=new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		response.setData(null);
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
