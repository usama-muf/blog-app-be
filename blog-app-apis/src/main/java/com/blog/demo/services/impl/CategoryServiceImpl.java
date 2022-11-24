package com.blog.demo.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.demo.entities.Category;
import com.blog.demo.exceptions.ResourceAlreadyAvalilableException;
import com.blog.demo.exceptions.ResourceNotFoundException;
import com.blog.demo.payloads.CategoryDto;
import com.blog.demo.repositories.CategoryRepository;
import com.blog.demo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		//checks whether category already present or not 
		int result = checkCategoryExists(categoryDto.getCategory());
		if(result != 0) throw new ResourceAlreadyAvalilableException(categoryDto.getCategory());
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		category = this.categoryRepository.save(category);
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepository.findAll();
		
		List<CategoryDto> categororiesDto = categories
				.stream()
				.map(category-> this.modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
		
		return categororiesDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> 
				new ResourceNotFoundException("Category: update Category ", "id ", categoryId, HttpStatus.NOT_FOUND));
		
		int result = checkCategoryExists(categoryDto.getCategory());
		if(result != 0) throw new ResourceAlreadyAvalilableException(categoryDto.getCategory());
		
		category.setCategory(categoryDto.getCategory());
		category.setCategoryDescription(categoryDto.getCategoryDescription());	
		
		Category updatedCategory = this.categoryRepository.save(category);
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}
	

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> 
				new ResourceNotFoundException("Category: update Category ", "id ", categoryId, HttpStatus.NOT_FOUND));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> 
				new ResourceNotFoundException("Category: Deleted Category ", "id ", categoryId, HttpStatus.NOT_FOUND));
		
		this.categoryRepository.delete(category);

	}
	
	private int checkCategoryExists(String categoryName) {
		int result =  this.categoryRepository.checkCategoryExist(categoryName);

		return result;
		
	}
	
	
}
