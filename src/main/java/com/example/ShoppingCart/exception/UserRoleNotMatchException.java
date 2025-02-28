package com.example.ShoppingCart.exception;

public class UserRoleNotMatchException extends RuntimeException{
	private String message;
	
	public UserRoleNotMatchException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
}
