package com.sangeetha.pagination.paginationDemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sangeetha.pagination.paginationDemo.dto.PostDto;
import com.sangeetha.pagination.paginationDemo.dto.PostResponse;
import com.sangeetha.pagination.paginationDemo.service.PostService;
import com.sangeetha.pagination.paginationDemo.utils.AppConstants;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
	private PostService postService;
	
	
	@PostMapping
	public ResponseEntity<PostDto>  createPost(@RequestBody PostDto postDto){
		
		PostDto createPost = postService.createPost(postDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createPost);
	}
	
	@GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

}
