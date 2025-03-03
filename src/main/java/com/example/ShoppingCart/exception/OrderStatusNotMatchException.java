package com.example.ShoppingCart.exception;

public class OrderStatusNotMatchException extends RuntimeException{
	private String message;
	public OrderStatusNotMatchException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
}
