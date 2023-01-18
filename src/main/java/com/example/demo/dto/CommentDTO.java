package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

public class CommentDTO {
    private Long id;
    @NotEmpty(message = "Should not be empty")
    @Size(min = 2, message = "The name of the post is too short")
    private String name;

    @NotEmpty(message = "Should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Should not be empty")
    @Size(min = 10, message = "The Content of the post is too short")
    private String body;

    public CommentDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommentDTO(Long id, String name, String email, String body) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



}
