package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class PostAppException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public PostAppException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PostAppException(HttpStatus status, String message, String message_1) {
        this.status = status;
        this.message = message;
        this.message = message_1;
    }


}
