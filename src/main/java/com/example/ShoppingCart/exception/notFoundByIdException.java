package com.example.ShoppingCart.exception;

public class notFoundByIdException extends RuntimeException{
	public notFoundByIdException(String message) {
		super(message);
	}
}
