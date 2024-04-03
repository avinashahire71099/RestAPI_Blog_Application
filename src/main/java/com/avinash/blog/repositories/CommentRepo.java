package com.avinash.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avinash.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
