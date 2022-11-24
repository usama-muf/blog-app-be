package com.blog.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.demo.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("SELECT count(c.id) FROM Category c WHERE c.category = ?1")
	int checkCategoryExist(String categoryName);

}
