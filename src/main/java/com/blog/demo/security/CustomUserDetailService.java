package com.blog.demo.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blog.demo.entities.User;
import com.blog.demo.exceptions.CustomInvalidCredentialsException;
import com.blog.demo.exceptions.ResourceNotFoundException;
import com.blog.demo.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		
		User user =  this.userRepository.findByEmail(username).orElseThrow(()->new CustomInvalidCredentialsException());		
		return user;
	
	}
	
	

}
