package com.commarket.commarket.dto.request;

public class PostUpdateRequest {
    private String title;
    private Integer price;
    private Long categoryId;

    public String getTitle() { return title; }
    public Integer getPrice() { return price; }
    public Long getCategoryId() { return categoryId; }
}
