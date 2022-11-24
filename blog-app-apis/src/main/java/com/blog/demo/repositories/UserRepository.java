package com.blog.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
