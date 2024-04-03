package com.avinash.blog.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinash.blog.entities.User;
import com.avinash.blog.exceptions.ResourceNotFoundException;
import com.avinash.blog.payloads.UserDto;
import com.avinash.blog.repositories.UserRepo;
import com.avinash.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		UserDto userDto = this.userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id", userId));
		this.userRepo.delete(user);
		
	}

	private User dtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto, User.class);
		
		/*
		 * user.setId(userDto.getId()); user.setName(userDto.getName());
		 * user.setPassword(userDto.getPassword()); user.setEmail(userDto.getEmail());
		 * user.setAbout(userDto.getAbout());
		 */
		return user;
	}       
	
	private UserDto userToDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		/*
		 * userDto.setId(user.getId()); userDto.setName(user.getName());
		 * userDto.setPassword(user.getPassword()); userDto.setEmail(user.getEmail());
		 * userDto.setAbout(user.getAbout());
		 */
		return userDto;
	}
	
}
