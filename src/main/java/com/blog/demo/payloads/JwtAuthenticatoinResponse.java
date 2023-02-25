package com.blog.demo.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class JwtAuthenticatoinResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
