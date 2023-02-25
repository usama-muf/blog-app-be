package com.blog.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.entities.Comments;
import com.blog.demo.entities.Post;

public interface CommentsRepo extends JpaRepository<Comments, Long>{
	List<Comments> findByPost(Post post);
}
