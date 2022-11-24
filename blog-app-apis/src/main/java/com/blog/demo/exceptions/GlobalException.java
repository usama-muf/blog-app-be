package com.blog.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.demo.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalException {

	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, 400);
	
		return  new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ResourceAlreadyAvalilableException.class})
	public ResponseEntity<ApiResponse> resourceAlreadyAvailable(ResourceAlreadyAvalilableException ex){
		
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, 500);
	
		return  new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> validationException(ConstraintViolationException ex) {
		
		Map<String, String> errors = new HashMap<>();
		ex.getConstraintViolations().forEach((error) ->{
			String name = error.getMessageTemplate().toString();
			String message = error.getPropertyPath().toString();
			errors.put(name, message);
		});
		
		return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);
		
	}
	
}
