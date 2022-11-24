package com.blog.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDto {
	
	
	private int categoryId;
	private String category;
	private String categoryDescription;
}
