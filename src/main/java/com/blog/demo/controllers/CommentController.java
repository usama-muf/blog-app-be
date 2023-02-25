package com.blog.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.payloads.CommentResponseDto;
import com.blog.demo.payloads.CommentsRequestDto;
import com.blog.demo.services.CommentsService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired 
	public CommentsService commentsService;
	
	@PostMapping("/post/{postId}/newcomment")
	public void createComment (@PathVariable("postId") Long postId, @RequestBody CommentsRequestDto commentsRequestDto ) {
		
		System.out.format("entered comment for post with id {} , {}", postId, commentsRequestDto);
		this.commentsService.createCommnet(commentsRequestDto, postId);
		return;	
	}
	
	@GetMapping("/allcomments/{postId}")
	public ResponseEntity<List<CommentResponseDto>> getAllComments(@PathVariable("postId") Long postId) {
		return ResponseEntity.ok(this.commentsService.listAllComments(postId));
	}
	
}
