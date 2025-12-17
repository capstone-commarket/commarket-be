package com.commarket.commarket.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    // ERD: INT
    @Column(name = "active", nullable = false)
    private boolean active;

    // ERD: INT
    @Column(name = "state", nullable = false)
    private Integer state;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private PostDetail detail;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> images = new ArrayList<>();

    protected Post() {}

    public Post(Long categoryId, Long userId, String title, Integer price) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.title = title;
        this.price = price;
        this.active = true; // 기본 활성
        this.state = 1;  // 판매중
        this.createdAt = LocalDateTime.now();
    }

    public void update(String title, Integer price, Long categoryId) {
        this.title = title;
        this.price = price;
        this.categoryId = categoryId;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setDetail(PostDetail detail) {
        this.detail = detail;
        if (detail != null) {
            detail.setPost(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }


    // Getter
}
