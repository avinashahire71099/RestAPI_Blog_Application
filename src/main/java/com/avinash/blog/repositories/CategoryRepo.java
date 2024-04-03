package com.avinash.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avinash.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
