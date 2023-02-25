package com.blog.demo.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.demo.config.DefaultValues;
import com.blog.demo.payloads.ApiResponse;
import com.blog.demo.payloads.PostDto;
import com.blog.demo.payloads.PostResponse;
import com.blog.demo.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Long userId,
			@PathVariable Long categoryId){   //, @RequestPart ("file") MultipartFile file) {
		PostDto post = this.postService.createPost(postDto, userId, categoryId);
//		System.out.print(file.getName());
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Long postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully!", HttpStatus.OK.value()),
				HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Long postId) {
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPost);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name = "pageNumber", defaultValue = DefaultValues.pageNumber, required = false ) Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = DefaultValues.pageSize, required = false) Integer pageSize,
			@RequestParam(name = "orderBy", defaultValue = DefaultValues.orderBy, required = false) String orderBy,
			@RequestParam(name = "isAsc", defaultValue = DefaultValues.isAsc, required = false) String isAsc) {
		 
		PostResponse allPosts =  this.postService.listAllPosts(pageNumber, pageSize, orderBy, isAsc);
		return ResponseEntity.ok(allPosts);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> listPostById(@PathVariable Long postId) {
		PostDto post = this.postService.listPostById(postId);
		return ResponseEntity.ok(post);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable Long categoryId) {
		List<PostDto> allPosts = this.postService.listPostsByCategoryId(categoryId);
		return ResponseEntity.ok(allPosts);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Long userId) {
		List<PostDto> allPosts = this.postService.listPostsByUserId(userId);
		return ResponseEntity.ok(allPosts);
	}
	
	@GetMapping("/posts/search")
	public ResponseEntity<List<PostDto>> searchResult(@RequestParam(name = "title", required = false) String title) {
		List<PostDto> searchedResult = this.postService.searchPost(title);
		return ResponseEntity.ok(searchedResult);
	}
}
