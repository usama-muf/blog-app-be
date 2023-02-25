package com.blog.demo.services;

import java.util.List;

import com.blog.demo.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto, Long userId);
	UserDto getUserById(Long userId);
	List<UserDto> getAllUser();
	void deleteUser(Long userId);
	

}
