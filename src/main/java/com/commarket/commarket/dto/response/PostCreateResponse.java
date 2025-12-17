package com.commarket.commarket.dto.response;

public class PostCreateResponse {
    private Long postId;
    private String message;

    public PostCreateResponse(Long postId, String message) {
        this.postId = postId;
        this.message = message;
    }

    public Long getPostId() { return postId; }
    public String getMessage() { return message; }
}
