package com.blog.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.demo.entities.Post;
import com.blog.demo.payloads.PostDto;
import com.blog.demo.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto dto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	PostResponse listAllPosts(Integer pageNumber, Integer pageSize);
	
	PostDto listPostById(Integer postId);
	
	List<PostDto> listPostsByCategoryId(Integer categoryId);
	
	List<PostDto> listPostsByUserId(Integer userId);

}
