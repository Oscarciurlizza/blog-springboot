package com.example.demo.services;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.PostResponse;

import java.util.List;

public interface PostService {
    public PostDTO createPost(PostDTO postDTO);

    public PostResponse getPosts(Integer pageNumber, Integer amountPage, String sortBy, String sortDir);

    public PostDTO getPostById(Long id);

    public PostDTO updatePost(PostDTO postDTO, Long id);

    public void deletePost(Long id);
}
