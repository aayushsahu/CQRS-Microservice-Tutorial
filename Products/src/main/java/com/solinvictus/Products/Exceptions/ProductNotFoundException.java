package com.solinvictus.Products.Exceptions;

public class ProductNotFoundException extends RuntimeException {
	 
	public ProductNotFoundException(String message) {
		super(message);
	}
}
