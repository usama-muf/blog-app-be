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
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.payloads.ApiResponse;
import com.blog.demo.payloads.CategoryDto;
import com.blog.demo.payloads.UserDto;
import com.blog.demo.services.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto dto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(dto, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
		
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, id);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		
		return ResponseEntity.ok(this.categoryService.getAllCategory());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
		CategoryDto category = this.categoryService.getCategoryById(id);
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Created Successfully !!", HttpStatus.OK.value()), HttpStatus.OK );
	}
	
}
