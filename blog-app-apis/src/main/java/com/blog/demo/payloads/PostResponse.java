package com.blog.demo.payloads;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class PostResponse {
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private long totalContents;
	private boolean isLastPage;
	private boolean isFirstPage;
	
	
}
