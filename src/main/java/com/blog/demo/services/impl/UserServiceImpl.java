package com.blog.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.demo.entities.User;
import com.blog.demo.exceptions.ResourceNotFoundException;
import com.blog.demo.payloads.UserDto;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToEntity(userDto);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		User savedUser = this.userRepository.save(user);

		return this.entityToDto(savedUser);
	}

	@Override
	public List<UserDto> getAllUser() {

		List<User> users = this.userRepository.findAll();

		List<UserDto> userDtos = users.stream().map(user -> this.entityToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		User user = this.userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", " id: UpdateUser ", userId, HttpStatus.BAD_REQUEST));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepository.save(user);

		return this.entityToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = this.userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id: GetUserById", userId, HttpStatus.NOT_FOUND));

		return this.entityToDto(user);
	}

	@Override
	public void deleteUser(Long userId) {
		User user = this.userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id: DeleteUser ", userId, HttpStatus.NOT_FOUND));

		this.userRepository.delete(user);
	}

	// using modelMapper to convert one object data to another object data
	private User dtoToEntity(UserDto dto) {
		User user = this.modelMapper.map(dto, User.class);
		return user;
	}

	private UserDto entityToDto(User user) {
		UserDto dto = this.modelMapper.map(user, UserDto.class);

		return dto;
	}
}
