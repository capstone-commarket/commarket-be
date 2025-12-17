package com.commarket.commarket.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_images")
public class PostImage {

    // image_id BIGINT PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    //  post_id BIGINT FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    //  정렬순서 sort_order INT NOT NULL
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected PostImage() {
    }

    public PostImage(Integer sortOrder) {
        this.sortOrder = sortOrder;
        this.createdAt = LocalDateTime.now();
    }

    void setPost(Post post) {
        this.post = post;
    }

    // ===== Getter =====
    public Long getImageId() {
        return imageId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
