package com.commarket.commarket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ratings",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","post_id"})
)
public class Rating {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "score")
    private Integer score;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Rating(Long userId, Long postId, Integer score) {
        this.userId = userId;
        this.postId = postId;
        this.score = score;
    }
}