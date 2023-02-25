package com.blog.demo.exceptions;

import org.springframework.http.HttpStatus;

public class CustomInvalidCredentialsException extends RuntimeException {

	public CustomInvalidCredentialsException() {
		super(String.format("Invalid Credentials: "+HttpStatus.BAD_REQUEST));
	}	

}
