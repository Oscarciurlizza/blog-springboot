package com.example.demo.dto;

import java.util.List;

public class PostResponse {
    private List<PostDTO> content;
    private Integer numberPage;
    private Integer amountPage;

    public List<PostDTO> getContent() {
        return content;
    }

    public void setContent(List<PostDTO> content) {
        this.content = content;
    }

    public Integer getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(Integer numberPage) {
        this.numberPage = numberPage;
    }

    public Integer getAmountPage() {
        return amountPage;
    }

    public void setAmountPage(Integer amountPage) {
        this.amountPage = amountPage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public PostResponse() {
        super();
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLastPage() {
        return lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.lastPage = lastPage;
    }

    private Long totalElements;
    private Integer totalPages;

    private Boolean lastPage;
}
