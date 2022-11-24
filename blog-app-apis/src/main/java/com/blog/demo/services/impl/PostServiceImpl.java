package com.blog.demo.services.impl;

import java.util.Date;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.demo.entities.Category;
import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;
import com.blog.demo.exceptions.ResourceNotFoundException;
import com.blog.demo.payloads.CategoryDto;
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
	
//	@Autowired
//	private PostResponse postResponse;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		Category category = getCategoryById(categoryId);

		User user = getUserById(userId);
		Post createdPost = this.modelMapper.map(postDto, Post.class);
		createdPost.setImageUrl("default.png");
		createdPost.setAddedDate(new Date());

		createdPost.setCategory(category);
		createdPost.setUser(user);

		Post newPost = this.postRepository.save(createdPost);

		return this.modelMapper.map(newPost, PostDto.class); // this.modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = getPostById(postId);
		PostDto updatedPostDto = this.modelMapper.map(post, PostDto.class);
		updatedPostDto.setTitle(postDto.getTitle());
		updatedPostDto.setContent(postDto.getContent());
		updatedPostDto.setImageUrl(postDto.getImageUrl());
		
		Post updatedPost = this.postRepository.save(this.modelMapper.map(updatedPostDto, Post.class));
		
		
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = getPostById(postId);
		this.postRepository.delete(post);

	}
	

	@Override
	public PostDto listPostById(Integer postId) {

		Post post = getPostById(postId);
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse listAllPosts(Integer pageNumber, Integer pageSize) {
		
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		
		Page<Post> pagePost = this.postRepository.findAll(page);
		List<Post> posts = pagePost.getContent();
		
		
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
	public List<PostDto> listPostsByCategoryId(Integer categoryId) {
		Category category = getCategoryById(categoryId);
		List<Post> listbyCategory = this.postRepository.findByCategory(category);
		List<PostDto> posts = listbyCategory.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return posts;

	}

	@Override
	public List<PostDto> listPostsByUserId(Integer userId) {
		User user = getUserById(userId);
		List<Post> listbyUserId = this.postRepository.findByUser(user);
		List<PostDto> posts = listbyUserId.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return posts;

	}

	private Category getCategoryById(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id in Create Post ", categoryId,
						HttpStatus.NOT_FOUND));

		return category;
	}

	private User getUserById(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User ", "User Id in Create Post ", userId, HttpStatus.NOT_FOUND));

		return user;
	}

	private Post getPostById(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post ", "PostId not found ", postId, HttpStatus.NOT_FOUND));
		return post;
	}

}
