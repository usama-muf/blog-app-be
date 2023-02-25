 package com.blog.demo.services.impl;

import java.util.Date;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.demo.entities.Category;
import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;
import com.blog.demo.exceptions.ResourceNotFoundException;
import com.blog.demo.payloads.PostDto;
import com.blog.demo.payloads.PostResponse;
import com.blog.demo.repositories.CategoryRepository;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.repositories.UserRepository;
import com.blog.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {

		Category category = getCategoryById(categoryId);

		User user = getUserById(userId);
		Post createdPost = this.modelMapper.map(postDto, Post.class);
		createdPost.setImageUrl("default.png");
		createdPost.setAddedDate(new Date());

		createdPost.setCategory(category);
		createdPost.setUser(user);

		System.out.println(createdPost);
		Post newPost = this.postRepository.save(createdPost);

		return this.modelMapper.map(newPost, PostDto.class); // this.modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long postId) {

		Post post = getPostById(postId);
		PostDto updatedPostDto = this.modelMapper.map(post, PostDto.class);
		updatedPostDto.setTitle(postDto.getTitle());
		updatedPostDto.setContent(postDto.getContent());
		updatedPostDto.setImageUrl(postDto.getImageUrl());

		Post updatedPost = this.postRepository.save(this.modelMapper.map(updatedPostDto, Post.class));

		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Long postId) {
		Post post = getPostById(postId);
		this.postRepository.delete(post);

	}

	@Override
	public PostDto listPostById(Long postId) {

		Post post = getPostById(postId);
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse listAllPosts(Integer pageNumber, Integer pageSize, String orderBy, String isAsc) {

//		Sort sort = isAsc.equalsIgnoreCase("ASC") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
		Pageable page = PageRequest.of(pageNumber, pageSize); // sort is to be implemented later

		Page<Post> pagePost = this.postRepository.findAll(page);
		List<Post> posts = pagePost.getContent();
		
		System.out.println(this.postRepository.findAll(page));

		List<PostDto> postsDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalContents(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setFirstPage(pagePost.isFirst());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public List<PostDto> listPostsByCategoryId(Long categoryId) {
		Category category = getCategoryById(categoryId);
		List<Post> listbyCategory = this.postRepository.findByCategory(category);
		List<PostDto> posts = listbyCategory.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return posts;

	}

	@Override
	public List<PostDto> listPostsByUserId(Long userId) {
		User user = getUserById(userId);
		List<Post> listbyUserId = this.postRepository.findByUser(user);
		List<PostDto> posts = listbyUserId.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return posts;

	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		List<Post> results = this.postRepository.findByTitleContaining(keyword);
		List<PostDto> searchResult = results.stream().map((result) -> this.modelMapper.map(result, PostDto.class))
				.collect(Collectors.toList());

		return searchResult;
	}

	private Category getCategoryById(Long categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id in Create Post ", categoryId,
						HttpStatus.NOT_FOUND));

		return category;
	}

	private User getUserById(Long userId) {
		User user = this.userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User ", "User Id in Create Post ", userId, HttpStatus.NOT_FOUND));

		return user;
	}

	private Post getPostById(Long postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post ", "PostId not found ", postId, HttpStatus.NOT_FOUND));
		return post;
	}

}
