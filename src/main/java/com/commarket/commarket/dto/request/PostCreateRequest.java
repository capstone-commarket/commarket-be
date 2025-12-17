package com.commarket.commarket.dto.request;

public class PostCreateRequest {
    private String title;
    private Integer price;
    private Long categoryId;

    private Integer tradeMethod;
    private String place;
    private String description;

    public String getTitle() { return title; }
    public Integer getPrice() { return price; }
    public Long getCategoryId() { return categoryId; }
    public Integer getTradeMethod() { return tradeMethod; }
    public String getPlace() { return place; }
    public String getDescription() { return description; }
}
