package com.example.ShoppingCart.exception;

public class UserNotFoundByIdException extends RuntimeException{
	public UserNotFoundByIdException(String message) {
		super(message);
	}
}
