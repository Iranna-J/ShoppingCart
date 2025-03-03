package com.example.ShoppingCart.exception;

public class ProductAlreadyExistException extends RuntimeException{
	public ProductAlreadyExistException(String message) {
		super(message);
	}
}
