package com.avinash.blog.services;

import java.util.List;

import com.avinash.blog.entities.User;
import com.avinash.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
