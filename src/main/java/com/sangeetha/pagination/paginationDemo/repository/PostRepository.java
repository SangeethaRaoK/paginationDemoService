package com.sangeetha.pagination.paginationDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangeetha.pagination.paginationDemo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
