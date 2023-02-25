package com.blog.demo.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.blog.demo.payloads.PostDto;
import com.blog.demo.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto dto, Long userId, Long categoryId);

	PostDto updatePost(PostDto postDto, Long postId);

	void deletePost(Long postId);

	PostResponse listAllPosts(Integer pageNumber, Integer pageSize, String orderBy, String isAsc);
	
	PostDto listPostById(Long postId);
	
	List<PostDto> listPostsByCategoryId(Long categoryId);
	
	List<PostDto> listPostsByUserId(Long userId);
	
	List<PostDto> searchPost(String keyword);

}
