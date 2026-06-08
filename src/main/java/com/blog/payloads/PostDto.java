package com.blog.payloads;

import java.util.Date;


import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	
	@NotEmpty
	@Size(min = 0, message = "Post title must be min of 4 characters")
	private String title;
	
	@NotEmpty
	@Size(min =0, message = "Post content must be min of 10 characters")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	
}
