package com.sangeetha.pagination.paginationDemo.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sangeetha.pagination.paginationDemo.dto.PostDto;
import com.sangeetha.pagination.paginationDemo.dto.PostResponse;
import com.sangeetha.pagination.paginationDemo.entity.Post;
import com.sangeetha.pagination.paginationDemo.repository.PostRepository;
import com.sangeetha.pagination.paginationDemo.service.PostService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@AllArgsConstructor

public class postServiceImpl implements PostService{
	
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub
		
		Post post=mapToEntity(postDto);
		Post newPost = postRepository.save(post);
		PostDto postResponse=mapToDto(newPost);
		return postResponse;
	}

	private PostDto mapToDto(Post post) {
		PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content= listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        
        return postResponse;
}
	
	private Post mapToEntity(PostDto postDto) {
		Post post=new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		
		return post;
		
	}

}
