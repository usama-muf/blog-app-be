package com.blog.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.entities.Category;
import com.blog.demo.entities.Post;
import com.blog.demo.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findByCategory(Category category);
	public List<Post> findByUser(User user);
}
