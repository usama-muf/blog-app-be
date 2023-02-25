package com.blog.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.exceptions.CustomInvalidCredentialsException;
import com.blog.demo.payloads.JwtAuthRequest;
import com.blog.demo.payloads.JwtAuthenticatoinResponse;
import com.blog.demo.security.JwtTokenHelper;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticatoinResponse> createToken(@RequestBody JwtAuthRequest authRequest) throws Exception {
		
		this.authenticate(authRequest.getUsername(), authRequest.getPassword());
		
		UserDetails userDetails =  this.userDetailsService.loadUserByUsername(authRequest.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthenticatoinResponse response = new JwtAuthenticatoinResponse();
		response.setToken(token);
		
		return new ResponseEntity<JwtAuthenticatoinResponse>(response, HttpStatus.OK);
		
	}
	public void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {			
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Exception at Auth Controller : "+e);
			throw new CustomInvalidCredentialsException();
		}
		
	}

	
	
}
