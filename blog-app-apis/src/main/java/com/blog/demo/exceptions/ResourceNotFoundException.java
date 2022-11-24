package com.blog.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{

	private String resourceName;
	private String fieldName;
	private long fieldValue;
	private HttpStatus httpStatus;
	
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, long id, HttpStatus httpStatus) {
		
		super(String.format("%s not found with %s : %s : HTTPStatus %s", resourceName, fieldName, id, httpStatus));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = id;
		this.httpStatus = httpStatus;
		
	}
	
	
}
