package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.exceptions.ResourceNotFoundException;
import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.payloads.UserDto;
import com.blog.repositories.RoleRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo UserRepo;
	
	@Autowired
	private ModelMapper ModelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
//	public void migratePasswords() {
//
//	    List<User> users = UserRepo.findAll();
//
//	    for (User user : users) {
//
//	        String password = user.getPassword();
//
//	        // ✅ Check if already encoded
//	        if (!password.startsWith("$2a$")) {
//
//	            String encodedPassword = passwordEncoder.encode(password);
//	            user.setPassword(encodedPassword);
//
//	            System.out.println("Updated user: " + user.getEmail());
//	        }
//	    }
//
//	    UserRepo.saveAll(users);
//	}
	
	@Override
	public UserDto createUser(UserDto UserDto) {
		User user =this.dtoToUser(UserDto);
		// IMPORTANT FIX
	    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		User savedUser = this.UserRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.UserRepo.findById(userId)
								 .orElseThrow(()-> new ResourceNotFoundException("User", " id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAbout(userDto.getAbout());
		
		// save to repository
		User updatedUser = this.UserRepo.save(user);
		
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.UserRepo.findById(userId)
				 .orElseThrow(()-> new ResourceNotFoundException("User", " id", userId));	
		return this.userToDto(user);
				 
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.UserRepo.findAll();
		
		List<UserDto> userDtos= users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.UserRepo.findById(userId)
				 .orElseThrow(()-> new ResourceNotFoundException("User", " id", userId));	
		this.UserRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto)
	{
		User user= this.ModelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
	}
	public UserDto userToDto(User user)
	{
		UserDto userDto = this.ModelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.ModelMapper.map(userDto, User.class);
		
		//encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		
		User newUser = this.UserRepo.save(user);
		return this.ModelMapper.map(newUser, UserDto.class);
	}
}
