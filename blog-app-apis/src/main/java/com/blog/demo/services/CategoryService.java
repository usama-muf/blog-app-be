package com.blog.demo.services;

import java.util.List;

import com.blog.demo.payloads.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	List<CategoryDto> getAllCategory();
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	CategoryDto getCategoryById(Integer caegoryId);
	void deleteCategory(Integer categoryId);
	
}
