package com.commarket.commarket.dto.response;

import java.time.LocalDateTime;

public class PostListResponse {
    private Long postId;
    private String title;
    private Integer price;
    private Long categoryId;
    private LocalDateTime createdAt;

    public PostListResponse(Long postId, String title, Integer price, Long categoryId, LocalDateTime createdAt) {
        this.postId = postId;
        this.title = title;
        this.price = price;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
    }

    public Long getPostId() { return postId; }
    public String getTitle() { return title; }
    public Integer getPrice() { return price; }
    public Long getCategoryId() { return categoryId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
