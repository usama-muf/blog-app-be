package com.blog.demo.services;

import java.util.List;

import com.blog.demo.payloads.CommentResponseDto;
import com.blog.demo.payloads.CommentsRequestDto;

public interface CommentsService {
	
	public void createCommnet(CommentsRequestDto commentReqDto, Long postId);
	public List<CommentResponseDto> listAllComments(Long postId); 
	
	

}
