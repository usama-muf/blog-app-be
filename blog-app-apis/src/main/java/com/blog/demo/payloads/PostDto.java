package com.blog.demo.payloads;

import java.util.Date;

import com.blog.demo.entities.Category;
import com.blog.demo.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PostDto {

	private Integer postId;
	private String title;
	private String content;
	private String imageUrl;
	private Date addedDate;
	private UserDto user;
	private CategoryDto category;

}
