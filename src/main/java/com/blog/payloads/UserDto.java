package com.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.blog.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min =0, message = "Username must be min of 4 characters")
	private String name;	
	
	@Email(message= "email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min= 3, max= 10, message = "password must be min of 3 char and max of 10 char")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
}
