package com.blog.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.entities.Post;
import com.blog.demo.payloads.ApiResponse;
import com.blog.demo.payloads.PostDto;
import com.blog.demo.payloads.PostResponse;
import com.blog.demo.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto post = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully!", HttpStatus.OK.value()),
				HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId) {
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPost);
	}

	 @GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false ) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		PostResponse allPosts =  this.postService.listAllPosts(pageNumber, pageSize);
		return ResponseEntity.ok(allPosts);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> listPostById(@PathVariable Integer postId) {
		PostDto post = this.postService.listPostById(postId);
		return ResponseEntity.ok(post);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable Integer categoryId) {
		List<PostDto> allPosts = this.postService.listPostsByCategoryId(categoryId);
		return ResponseEntity.ok(allPosts);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Integer userId) {
		List<PostDto> allPosts = this.postService.listPostsByUserId(userId);
		return ResponseEntity.ok(allPosts);
	}
}
