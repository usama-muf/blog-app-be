package com.blog.demo.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.demo.entities.Comments;
import com.blog.demo.entities.Post;
import com.blog.demo.exceptions.ResourceNotFoundException;
import com.blog.demo.payloads.CommentResponseDto;
import com.blog.demo.payloads.CommentsRequestDto;
import com.blog.demo.repositories.CommentsRepo;
import com.blog.demo.repositories.PostRepository;
import com.blog.demo.services.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentsRepo commentsRepo;
	
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public void createCommnet(CommentsRequestDto commentReqDto, Long postId) {		// TODO Auto-generated method stub
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Comments Service impl ", "PostId not found ", postId, HttpStatus.NOT_FOUND));
		Comments comment = this.modelMapper.map(commentReqDto, Comments.class);
		
		comment.setPost(post);
		this.commentsRepo.save(comment);
		return;
		
	}


	@Override
	public List<CommentResponseDto> listAllComments(Long postId) {
		
		Post postFromId = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Comments Service impl ", "PostId not found ", postId, HttpStatus.NOT_FOUND));
		List<Comments> allPosts = this.commentsRepo.findByPost(postFromId);
		
		List<CommentResponseDto> listComments= allPosts.stream()
				.map(post -> this.modelMapper.map(post, CommentResponseDto.class)).collect(Collectors.toList());
		
		return listComments;
	}

	
	
}
