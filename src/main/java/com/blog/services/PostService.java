package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	// get post by id
	PostDto getPostById(Integer postId);
	
	// get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	// get all post by a user
	List<PostDto> getPostByUser(Integer userId);
	
	// search posts
	List<PostDto> searchPosts(String keyword);
	
}
