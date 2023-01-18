package com.example.demo.dto;

import com.example.demo.models.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class PostDTO {


    private Long id;

    @NotEmpty(message = "Should not be empty")
    @Size(min = 2, message = "The Title of the post is too short")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "The Description of the post is too short")
    private String description;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    private Set<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public PostDTO() {
        super();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
}
