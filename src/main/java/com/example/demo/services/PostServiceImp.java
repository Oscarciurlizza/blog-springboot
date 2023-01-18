package com.example.demo.services;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.PostResponse;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = mappinEntity(postDTO);
        Post newPost = postRepository.save(post);
        PostDTO postResponse = mappingDTO(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getPosts(Integer pageNumber, Integer amountPage, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, amountPage, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listPosts = posts.getContent();
        List<PostDTO> content = listPosts.stream().map(post -> mappingDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setNumberPage(posts.getNumber());
        postResponse.setAmountPage(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mappingDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        Post udpatePost = postRepository.save(post);
        return mappingDTO(udpatePost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }


    //Convert Entity to DTO
    private PostDTO mappingDTO(Post post) {
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        return postDTO;
    }

    //Convert DTO to Entity
    private Post mappinEntity(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        return post;
    }
}
