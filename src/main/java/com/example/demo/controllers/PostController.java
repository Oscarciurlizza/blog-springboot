package com.example.demo.controllers;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.PostResponse;
import com.example.demo.services.PostService;
import com.example.demo.utils.AppConstanst;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public PostResponse listPosts(@RequestParam(value = "page", defaultValue = AppConstanst.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(value = "size", defaultValue = AppConstanst.DEFAULT_PAGE_SIZE, required = false) Integer amountPage, @RequestParam(value = "sortBy", defaultValue = AppConstanst.SORT_BY_DEFAULT, required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = AppConstanst.SORT_ADDRESS_BY_DEFAULT, required = false) String sortDir) {
        return postService.getPosts(pageNumber, amountPage, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable(name = "id") Long id) {
        PostDTO postResponse = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
