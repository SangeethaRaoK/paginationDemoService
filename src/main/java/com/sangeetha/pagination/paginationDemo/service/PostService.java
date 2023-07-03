package com.sangeetha.pagination.paginationDemo.service;

import com.sangeetha.pagination.paginationDemo.dto.PostDto;
import com.sangeetha.pagination.paginationDemo.dto.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto);
	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) ;

}
