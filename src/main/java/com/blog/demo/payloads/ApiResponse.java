package com.blog.demo.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor

public class ApiResponse {

	private String message;
	private int success;
	
	public ApiResponse(String message, int status) {
		this.message = message;
		this.success = status;
	}
	
}
