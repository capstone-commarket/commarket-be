package com.commarket.commarket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "likes",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","post_id"})
)
public class Favorite {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Favorite(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}