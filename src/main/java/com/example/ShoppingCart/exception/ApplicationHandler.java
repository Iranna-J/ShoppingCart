package com.example.ShoppingCart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ShoppingCart.config.ResponseStructure;

@RestControllerAdvice
public class ApplicationHandler {
	
//	@ExceptionHandler(UserRoleNotMatchException.class)
//	public ResponseEntity<ResponseStructure<String>> handleUserRoleNotMatch(UserRoleNotMatchException ex) {
//	    ResponseStructure<String> responseStructure = new ResponseStructure<>();
//	    responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
//	    responseStructure.setMessage("Invalid role");
//	    responseStructure.setData("Data is not created");
//
//	    return new ResponseEntity<>(responseStructure, HttpStatus.NO_CONTENT);
//	}
	
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseStructure<String>> handleInvalidEnum(IllegalArgumentException ex) {
        ResponseStructure<String> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
        responseStructure.setMessage(ex.getMessage());
        responseStructure.setData("Role must be one of: ADMIN, SELLER, CUSTOMER");

        return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<ResponseStructure<String>> handleInvalidMobileNumberException(InvalidMobileNumberException ex) {
	    ResponseStructure<String> response = new ResponseStructure<>();
	    response.setStatus(HttpStatus.BAD_REQUEST.value());
	    response.setMessage(ex.getMessage());
	    response.setData(null);

	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<ResponseStructure<String>> userNotFoundById(UserNotFoundByIdException ex){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessage());
		responseStructure.setData(null);
		
		return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}


}
